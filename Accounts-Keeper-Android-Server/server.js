var express = require('express'),
  bodyParser = require('body-parser'),
  mongoose = require('mongoose');

var db = require('./db');
mongoose.connect(db.url);

app = express();

app.set('port', process.env.PORT || 3000);
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
  extended: true
}));

app.use('/', require('./routes/route')(app));

module.exports = app
