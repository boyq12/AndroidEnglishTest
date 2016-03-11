var express = require('express');
var router = express.Router();
var mongo = require('mongodb');
var Server = mongo.Server,
	Db = mongo.Db,
	BSon = mongo.BSonPure;
var server = new Server('localhost', 27017, {auto_reconnect: true});
db = new Db('android-user', server);

db.open(function(err, db) {
    if (!err) {
        console.log("Connected to 'nodejsintro' database");
        db.collection('user', {strict: true}, function(err, collection) {
            if (err) {
                console.log("The 'user' collection doesn't exist. Creating it with sample data...");
                //at this point you should call your method for inserting documents.
            }
        });
    }
}); 
module.exports = router;
