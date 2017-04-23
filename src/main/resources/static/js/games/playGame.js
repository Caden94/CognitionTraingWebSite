function allowDrop(ev) {
	ev.preventDefault();
}

function drag(ev) {
	ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
	if (ev.target.firstChild == null && ev.target.nodeName == "DIV") {
		ev.preventDefault();
		var data = ev.dataTransfer.getData("text");
		ev.target.appendChild(document.getElementById(data));
	}
}
/** time * */
var start_time, end_time, timer, start_date, end_date;
function startClock() {
	timer = setInterval(myTimer, 1000);
	var date = new Date();
	start_time = date.getTime();
	start_date = date.toISOString();
}

function myTimer() {
	var date = new Date();
	end_time = date.getTime();
	end_date = date.toISOString();
	document.getElementById("time").innerHTML = "Timer: "
			+ Math.floor((end_time - start_time) / 1000);
}

function endClock() {
	clearInterval(timer);
	var time = Math.floor((end_time - start_time) / 1000);
	return time;
}

/** play * */
function play(filename) {
	document.ondragstart = function(event) {
		event.dataTransfer.setData("text", event.target.id);
	};
	var button = document.getElementById("play");
	if (button.innerHTML == "start") {
		document.getElementById("images").className = "display";
		button.innerHTML = "submit";
		startClock();
		creatingImage(filename);
	} else {
		button.innerHTML = "start";
		var duration = endClock();
		var score = rate(time);
		window.location = "/puzzle";
//		window.location = "/submit?duration=" + duration + "&score=" + score
//				+ "&start_date=" + start_date + "&end_date=" + end_date;
	}
}

function rate(time) {
	// Rate without time factor
	var result = 0;
	// get piles (td - div - img)(html collection)
	var piles = document.getElementsByTagName("td");
	// get piles id && image_id on pile
	var piles_id = [];
	var images_id = [];
	for (var i = 0; i < piles.length; i++) {
		var pile = piles.item(i).firstChild;
		piles_id.push(pile.getAttribute("id").split("_")[1]);
		if (pile.firstChild != null) {
			images_id.push(pile.firstChild.getAttribute("id").split("_")[1]);
		} else {
			images_id.push(-1);
		}
	}
	// Rating
	for (var i = 0; i < piles_id.length; i++) {
		if (piles_id[i] == images_id[i])
			result++;
	}
	result = Math.round(result * 100 / piles_id.length);
	alert("You got " + result + "/100");
	return result;
}

function submit(time, score) {
	return null;
}

//Create image
function creatingImage(filename) {
	// interval length
	var n = 3;
	for (var i = 0; i < n; i++) {
		for (var j = 0; j < n; j++) {
			cropImage(filename, i, j, n);
		}
	}
}

// Crop image
function cropImage(filename, i, j, n) {
	var image = new Image();
	var k = 600 / n;
	image.onload = function() {
		var sx = j * k;
		var sy = i * k;
		var sWidth = k;
		var sHeight = k;
		var dx = 0;
		var dy = 0;
		var dWidth = sWidth;
		var dHeight = sHeight;
		
		var canvas = document.createElement("canvas");
		canvas.id = "canvasId" + i * n + j;
		canvas.width = sWidth;
		canvas.height = sHeight;
		
		var context = canvas.getContext('2d');
		
		context.drawImage(image, sx, sy, sWidth, sHeight, dx, dy, dWidth, dHeight);
		
		var img = new Image();
		var id = i * n + j + 1;
		img.id = "image" + "_" + id;
		img.width = 100;
		img.height = 100;
		img.src = canvas.toDataURL();
		
		context.clearRect(0, 0, canvas.width, canvas.height);
		
		img.draggable = true;

		var container = document.getElementsByClassName("image");
		container[id - 1].appendChild(img);
	}
	
	image.src = "/files/" + filename;
}