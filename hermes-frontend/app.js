// Connexion au serveur Spring Boot
const socket = new SockJS('http://localhost:8080/hermes-ws');
const stompClient = Stomp.over(socket);

// Désactiver les logs de debug massifs dans la console
stompClient.debug = null; 

stompClient.connect({}, function (frame) {
    console.log('Connecté au backend !');
    
    // Mettre à jour la pastille de statut
    const statusDiv = document.getElementById('status');
    statusDiv.textContent = "Connecté";
    statusDiv.className = "status online";

    // S'abonner au flux des messages Twitch
    stompClient.subscribe('/topic/chat', function (message) {
        const messageData = JSON.parse(message.body);
        displayMessage(messageData);
    });
}, function(error) {
    console.error('Erreur de connexion WebSocket : ', error);
});

// Fonction pour injecter le message dans l'interface
function displayMessage(msg) {
    const chatBox = document.getElementById('chat-box');
    const messageElement = document.createElement('div');
    messageElement.classList.add('message-card');

    // Création des badges (Modérateur, Broadcaster, VIP)
    let badgesHtml = '';
    if (msg.isBroadcaster) badgesHtml += `<span class="badge broadcaster">🎥 Broadcaster</span>`;
    if (msg.isMod) badgesHtml += `<span class="badge mod">🛡️ Mod</span>`;
    if (msg.isVip) badgesHtml += `<span class="badge vip">💎 VIP</span>`;

    // Structure du message
    messageElement.innerHTML = `
        <div class="message-header">
            ${badgesHtml}
            <span class="username" style="color: ${msg.color}">${msg.username}</span>
        </div>
        <div class="message-body">
            ${msg.message}
        </div>
        <div class="message-actions">
            <button onclick="selectQuestion('${msg.id}')">Envoyer au Caster</button>
        </div>
    `;

    // Ajouter le message à la fin et faire défiler vers le bas
    chatBox.appendChild(messageElement);
    chatBox.scrollTop = chatBox.scrollHeight;
}

// Fonction pour envoyer l'action à Spring Boot 
function selectQuestion(id) {
    console.log("Question sélectionnée : ", id);
    // Code d'envoi vers /app/select-question à venir...
}