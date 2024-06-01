let playerIdInput = prompt("Enter your player ID (1, 2, or 3):");
let currentPlayer = parseInt(playerIdInput) - 1;
if (currentPlayer < 0 || currentPlayer > 2) {
    alert("Invalid player ID. Please reload the page and enter a valid player ID.");
}
let showReward = false;
let stompClient = null;

function connect() {
    const socket = new SockJS('/game-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/game', function (gameUpdate) {
            updateGameStatus(JSON.parse(gameUpdate.body));
        });
        sendStatusRequest();
    });
}

function sendStatusRequest() {
    stompClient.send("/app/status", {}, {});
}

function sendMoveRequest(playerId, angle) {
    stompClient.send("/app/move", {}, JSON.stringify({'playerId': playerId, 'angle': angle}));
}

function updateGameStatus(data) {
    let statusDiv = document.getElementById('game-status');
    let currentPlayerStatusDiv = document.getElementById('current-player-status');
    let canvas = document.getElementById('game-canvas');
    let ctx = canvas.getContext('2d');
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    statusDiv.innerHTML = '';

    if (showReward) {

        let rewardX = canvas.width / 2 + data.rewardX * 5;
        let rewardY = canvas.height / 2 - data.rewardY * 5;
        ctx.fillStyle = 'gold';
        ctx.beginPath();
        ctx.arc(rewardX, rewardY, 5, 0, 2 * Math.PI);
        ctx.fill();
    }

    data.players.forEach(player => {

        let playerX = canvas.width / 2 + player.x * 5;
        let playerY = canvas.height / 2 - player.y * 5;
        ctx.fillStyle = player.color;
        ctx.beginPath();
        ctx.arc(playerX, playerY, 5, 0, 2 * Math.PI);
        ctx.fill();
        ctx.fillText(`P${player.id + 1}`, playerX - 5, playerY - 10);


        statusDiv.innerHTML += `<div style="color: ${player.color};">Player ${player.id + 1}: (${player.x.toFixed(2)}, ${player.y.toFixed(2)}) - Distance to reward: ${player.distanceToReward.toFixed(2)}</div>`;
    });

    if (data.gameFinished) {
        currentPlayerStatusDiv.innerHTML = '<strong>Game Over!</strong>';
    } else {
        currentPlayerStatusDiv.innerHTML = `Current Player: Player ${data.currentPlayer + 1}`;
        document.querySelectorAll('.angle-input').forEach(el => el.classList.remove('active'));
        document.getElementById(`angle-input-${data.currentPlayer}`).classList.add('active');
    }
}

document.getElementById('move-form').addEventListener('submit', function (event) {
    event.preventDefault();
    let angleInput = document.querySelector(`#angle-${currentPlayer}`).value;
    if (angleInput) {
        sendMoveRequest(currentPlayer, angleInput);
    }
});

document.getElementById('show-reward-btn').addEventListener('click', function () {
    showReward = !showReward;
    sendStatusRequest();
});

connect();
