var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var url = 'mongodb://localhost:27017/android';


//connect to Database
mongoose.connect(url);
var AccountSchema = new Schema ({
    username:String,
    password:String,
    email:String
});

//log in API
router.post('/login', function(req, res) {
    var Account = mongoose.model('Account', AccountSchema, 'accounts');
    Account.find({'username':req.body.username}, function(err, docs){
        console.log(docs);
        if(docs.length>0){
            res.send('login success');
        }
        else {
            res.send('fail to login');
        }
    });
});


//regiser API
router.post('/register',function(req, res) {
    /*optional stuff to do after success */
    var Account = mongoose.model('Account', AccountSchema, 'accounts');
    var new_user = new Account({
        username: req.body.username,
        password: req.body.password,
        email: req.body.email
    });
    new_user.save(function(err,data) {
        // body...
        if(err) console.log(err);
        else console.log('register success');
    });
}); 
router.post('/resetpassword', function(req, res) {
    /*optional stuff to do after success */
    var Account = mongoose.model('Account', AccountSchema, 'accounts');
    Account.findOneAndUpdate({'username': req.body.username}, {'password': 'xac_cmn_dinh'}, function(err, data){
        if(err) {
            console.log(err);
            res.send('can change password');
        }
        else{
            res.send('change success');
        }
    });
});
    
module.exports = router;
