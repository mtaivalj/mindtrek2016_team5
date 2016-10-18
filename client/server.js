var express = require('express');
var app = express();
var exec = require('child_process').exec;

app.use(express.static('public'));
app.get('/index.html', function (req, res) {
   res.sendFile( __dirname + "/" + "index.html" );
});

app.use(express.static('public'));
app.get('/jquery.js', function (req, res) {
   res.sendFile( __dirname + "/" + "jquery.js" );
});

app.get('/exec', function (req, res) {
   // res.end(JSON.stringify(req.query));
   var command = req.query.command;

   var run = 'java -jar manual.jar "' + command+'"';

   child = exec(run, function (error, stdout, stderr) {
      // var json = JSON.parse(stdout);
      // res.end(JSON.stringify(json.data));
   });
})

var server = app.listen(8081, function () {
   var host = server.address().address
   var port = server.address().port
   console.log("Example app listening at http://%s:%s", host, port)
})
