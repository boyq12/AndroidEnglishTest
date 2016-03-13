var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
var mongo = require('mongodb');
var mongoClient = mongo.MongoClient;
var assert = require('assert');
var url = 'mongodb://localhost:27017/android-user';
/*var Server = mongo.Server,
	Db = mongo.Db,
	BSon = mongo.BSonPure;
var server = new Server('localhost', 27017, {auto_reconnect: true});
db = new Db('android-user', server);*/


router.post('/login', function(req, res) {
    mongoClient.connect(url, function(err, db) {
    console.log("Connecting.....")
    if (!err) {
        console.log("Connected to database");
    }
    var account = db.collection('account');
    var username = req.body.username;
    account.find(req.body, function(err, count) {
        // body...
        res.send(count.length);
    });
    db.close();
    }); 
    
});
router.post('/register',function(req,res){
 	mongoClient.connect(url, function(err, db) {
    console.log("Connecting.....")
    if (!err) {
        console.log("Connected to database");
    }
    var account = db.collection('account');
    account.insert({
        name:req.body.username,
        password:req.body.password,
        email:req.body.email
    });
    res.send("success");
    db.close();
    }); 
}); 
router.post('/resetpassword',function(req,res){
    mongoClient.connect(url, function(err, db) {
    console.log("Connecting.....")
    if (!err) {
        console.log("Connected to database");
    }
    var account = db.collection('account');
    account.findOneAndUpdate({name:req.body.name},{password:'1234566'});
    res.send("success");
    db.close();
    }); 
}); 
module.exports = router;
