var x = [];

var trace1 = {
    x: x,
    name: 'control',
    autobinx: false,
    histnorm: "count",
    marker: {
        color: "rgba(25, 109, 159, 0.7)",
        line: {
            color: "rgba(48, 84, 109, 1)",
            width: 1
        }
    },
    opacity: 0.5,
    type: "histogram",
    xbins: {
        end: 100,
        size: 1,
        start: 1
    }
};
var data = [trace1];
var layout = {
    bargap: 0.1,
    bargroupgap: 0.1,
    //barmode: "overlay",
    title: "Results distribution",
    xaxis: {title: "Results", range: [1, 100]},
    yaxis: {title: "Count"}
};

function showNumbers(message) {
    data[0].x.push(message);
    Plotly.newPlot('myDiv', data, layout, {staticPlot: true, responsive: false});

}

updateNumbers();
data[0].x = numbers;
Plotly.newPlot('myDiv', data, layout, {staticPlot: true, responsive: false});

function localRefresh() {
    $('#anchor').load("/numbers", function () {
            updateNumbers();
            data[0].x = numbers;
            Plotly.react('myDiv', data, layout, {staticPlot: true, responsive: false});
        }
    )
}

var stompClient = null;
var subscription = null;

function connect() {
    var headers = {
        login: 'fooLogin',
        passcode: 'fooPassword',
        'client-id': 'foo-client-id'
    };
    stompClient.connect({}, wsConnectSuccessCallback, wsConnectErrorCallback);
    stompClient.heartbeat.outgoing = 10000;
    stompClient.heartbeat.incoming = 10000;
}

function connectWithSockJS() {
    var url = 'http://' + window.location.host + '/numbers-websocket';
    var socket = new SockJS(url);
    stompClient = Stomp.over(socket);
    console.log("Connecting via SockJS...");
    connect();
}

function connectWithWebSocket() {
    var wsUrl = 'ws://' + window.location.host + '/numbers-websocket';
    stompClient = Stomp.client(wsUrl);
    console.log("Connecting via WebSocket...");
    connect();
}

function wsConnectSuccessCallback() {
    console.log("Connected!");
    localRefresh();
    var destination = '/loremipsum-ai-stream';
    subscription = stompClient.subscribe(destination, onReceiving, { id: 'foo-id' });
}

function wsConnectErrorCallback(error) {
    console.log("Not connected: " + error);
    //alert('Error while connecting to the server WebSocket: ' + error);
}

onReceiving = function(message) {
    if (message.body) {
        showNumbers(message.body);
    } else {
    }
}

//connectWithSockJS();
connectWithWebSocket();

function parseLocation() {
    console.log(window.location.origin);
    console.log(window.location.protocol);
    console.log(window.location.host);
    console.log(window.location.hostname);
    console.log(window.location.port);
}
parseLocation();
