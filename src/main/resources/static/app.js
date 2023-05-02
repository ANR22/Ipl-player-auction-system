var stompClient = null;
let timerStopSent = false;
let timer = 30;
function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
  
        console.log('Connected: ' + frame);
        stompClient.subscribe('/biddings/listen', function (bid) {
			console.log(bid)
            showBid(bid.body);
        });
    });
}
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendBid() {
	let teamId = document.getElementById("teamId").innerHTML;
	let playerId = document.getElementById("playerId").innerHTML
	let teamName = document.getElementById("teamName").innerHTML;
	if(timer >= 0){
		document.getElementById("timer").innerHTML = timer;
		console.log(teamName);
    	stompClient.send(`/app/bid`, {}, JSON.stringify({teamId: teamId,playerId:playerId,teamName:teamName}));
	}
	else{
		document.getElementById("timer").innerHTML = "Timer expired";
		
	}
 
}

function showBid(message) {
	
	json_message = JSON.parse(message);
	if(json_message.type == "bid"){
		console.log("bid");
		document.getElementById("currentBidAmount").innerHTML = json_message.bidAmount;
		document.getElementById("currentBidTeam").innerHTML = json_message.teamName
		timer = 30;
		document.getElementById("timer").style.display = "block";
	timerStopSent = false;
	}
	else if(json_message.type === "nextPlayer"){
		const {id,name,basePrice} = json_message;
		document.getElementById("playerId").innerHTML = id;
		document.getElementById("playerName").innerHTML = name;
		document.getElementById("basePrice").innerHTML = basePrice;
		document.getElementById("currentBidAmount").innerHTML = basePrice;
		document.getElementById("currentBidTeam").innerHTML = "No current bidders"
		document.getElementById("player-img").src = json_message.imgUrl;
		document.getElementById("timer").style.display = "block";
		timer = 30;
	timerStopSent = false;
		
	}else if(json_message.type === "auctionOver"){
		document.getElementById("playerId").innerHTML = "";
		document.getElementById("playerName").innerHTML = "";
		document.getElementById("basePrice").innerHTML = "";
		document.getElementById("currentBidAmount").innerHTML = "";
		document.getElementById("currentBidTeam").innerHTML = "";
		document.getElementById("Over").innerHTML = "Auction Over";
		document.getElementById("bid-container").style.display="none";
		document.getElementById("timer").style.display = "none";
	}
    
}

$(document).ready(function () {
	let teamId = document.getElementById("teamId").innerHTML;
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    //document.getElementById("timer") = timer;
    function updateTimer(){
	if(timer >= 0)
		timer -= 1;
	else{
		if(!timerStopSent){
			stompClient.send(`/app/timerstop`, {}, JSON.stringify({teamId: teamId}));
			timerStopSent = true;
		}
	}
	document.getElementById("timer").innerHTML = timer;
}
    const timerUpdate = setInterval(updateTimer, 1000);
    console.log("javascript");
    connect();
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#bid" ).click(function() { sendBid(); });
})

