var ws;

function connect() {
	var sender = document.getElementById("sender").value;

	var host = document.location.host;

	ws = new WebSocket("ws://" + host + "/chat/" + sender);
	// Khi nhận được tin nhắn từ server
	ws.onmessage = function(event) {
		var log = document.getElementById("log");
		var content = document.getElementById("msg").value;
		console.log(event.data);
		var message = JSON.parse(event.data);
		if (message.content != null) {
			log.innerHTML += message.from + " : " + message.content + "\n";
		} else if (message.from == sender) {
			log.innerHTML += message.from + " : " + content + "\n";
		} else {
			fetch('/decode', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				},
				body: event.data
			})
				.then(response => response.text())
				.then(data => {
					if (data != "")
						log.innerHTML += message.from + " : " + data + "\n";
				})
				.catch(error => {
					console.error('error', error);
				});
		}
	};


	// Khi xảy ra lỗi WebSocket
	ws.onerror = function(event) {
		console.error("WebSocket error observed:", event);
	};

	// Khi WebSocket bị đóng
	ws.onclose = function(event) {
		console.log("WebSocket is closed now.");
	};
}



function send() {
	var sender = document.getElementById("sender").value;
	var receiver = document.getElementById("receiver").value;
	var content = document.getElementById("msg").value;
	fetch('/code', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({
			"from": sender,
			"to": receiver,
			"content": content
		})
	})
		.then(response => response.json())
		.then(data => {
			document.getElementById("cipher").value = data;
			var json = JSON.stringify({
				"from": sender,
				"to": receiver,
				"ciphertext": data
			});
			ws.send(json);
		})
		.catch(error => {
			console.error('error', error);
		});
}
