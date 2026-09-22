<template>
  <div>
    <h2>Vue Audio Chat</h2>
    <button @click="startCall">Start Call</button>
    <button @click="endCall">End Call</button>
  </div>
</template>

<script setup>
import { ref } from "vue";

let pc;
let socket;
let localStream;

const startCall = async () => {
  socket = new WebSocket("ws://localhost:3000");

  pc = new RTCPeerConnection({
    iceServers: [{ urls: "stun:stun.l.google.com:19302" }]
  });

  localStream = await navigator.mediaDevices.getUserMedia({ audio: true });
  localStream.getTracks().forEach(track => pc.addTrack(track, localStream));

  pc.onicecandidate = e => {
    if (e.candidate) {
      socket.send(JSON.stringify({ type: "candidate", candidate: e.candidate }));
    }
  };

  pc.ontrack = e => {
    const audio = document.createElement("audio");
    audio.srcObject = e.streams[0];
    audio.autoplay = true;
  };

  socket.onmessage = async msg => {
    const data = JSON.parse(msg.data);

    if (data.type === "offer") {
      await pc.setRemoteDescription(data.offer);
      const answer = await pc.createAnswer();
      await pc.setLocalDescription(answer);
      socket.send(JSON.stringify({ type: "answer", answer }));
    }

    if (data.type === "answer") {
      await pc.setRemoteDescription(data.answer);
    }

    if (data.type === "candidate") {
      await pc.addIceCandidate(data.candidate);
    }
  };

  socket.onopen = async () => {
    const offer = await pc.createOffer();
    await pc.setLocalDescription(offer);
    socket.send(JSON.stringify({ type: "offer", offer }));
  };
};

const endCall = () => {
  pc?.close();
  socket?.close();
  localStream?.getTracks().forEach(t => t.stop());
};
</script>
