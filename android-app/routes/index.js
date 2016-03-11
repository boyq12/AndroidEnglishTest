var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
var mongo = require('mongodb');
var Server = mongo.Server,
	Db = mongo.Db,
	BSon = mongo.BSonPure;
var server = new Server('localhost', 27017, {auto_reconnect: true});
db = new Db('android-user', server);
db.open(function(err, db) {
    if (!err) {
        console.log("Connected to database");
        db.collection('user', {strict: true}, function(err, collection) {
            if (err) {
             	console.log('Wrong data!')   
            }
        });
    }
}); 
router.post('/login', function(req, res) {
	var user_json = db.collection('account').find(req.body.name).buf.toJSON(function(err,message){
		if(err) console.log('can not access data');
	})
	console.log(user_json.name);
});
 router.post('/register',function(req,res){
 	MongoClient.connect(URL, function(err,db){
 		if (err) {
 			console.log(err);
 			return;
 		}
 		var collection = db.collection('account')
 	});
 });
module.exports = router;
