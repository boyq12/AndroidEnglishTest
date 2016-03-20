var express = require('express');
var router = express.Router();
var HmacSHA256 = require('crypto-js/hmac-sha256');
var rand = require('csprng');
var bodyParser = require('body-parser');
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var url = 'mongodb://localhost:27017/android';


//connect to Database
mongoose.connect(url);
var AccountSchema = new Schema({
    username: String,
    password: String,
    email: String,
    key: String
});

//log in API
router.post('/login', function(req, res) {
    var Account = mongoose.model('Account', AccountSchema, 'accounts');
    Account.findOne({ 'username': req.body.username }, function(err, docs) {
        if (docs != null) {
            var password = HmacSHA256(req.body.password, docs.key);
            var email = HmacSHA256(req.body.email, docs.key);
            if (password == docs.password && email == docs.email) {
                res.send('login success');
            } else {
                res.send('Wrong password')
            }

        } else {
            res.send('no username');
        }
    });

});


//regiser API
router.post('/register', function(req, res) {
    /*optional stuff to do after success */
    var Account = mongoose.model('Account', AccountSchema, 'accounts');
    Account.findOne({ 'username': req.body.username }, function(err, users) {
        if (users !=[]) res.send('User registed')
        else {
            var key = rand(160, 36);
            var new_user = new Account({
                username: req.body.username,
                password: HmacSHA256(req.body.password, key),
                email: HmacSHA256(req.body.email, key),
                key: key
            });
            new_user.save(function(err, data) {
                // body...
                if (err) console.log(err);
                else res.send('register success');
            });
        }
    })
});
router.post('/resetpassword', function(req, res) {
    /*optional stuff to do after success */
    var Account = mongoose.model('Account', AccountSchema, 'accounts');
    Account.findOne({ 'username': req.body.username }, function(err, user) {
        if (err) console.log(err)
        if (user ==[]) console.log("no user")
        var new_password = rand(40, 36);
        var new_key = rand(160, 36);
        var res_body = {
            'title': String
        }

        Account.update({ 'username': user.username }, { 'key': new_key, 'password': HmacSHA256(new_password, new_key) }, { 'upsert': false, 'multi': false },
            function(err, numUpdated) {
                if (err) console.log(err)
                else res_body.title = "good"
                console.log(res_body)

            })
        res.send(new_password)
    });
     
});
router.post('/changepassword', function(req, res) {
    var Account = mongoose.model('Account', AccountSchema, 'accounts');
    Account.findOne({ 'username': req.body.username, 'email': req.body.email }, function(err, user) {
        if (err) console.log(err)
        else if (user == []) res.send("Wrong user or Email")
        else {
            var new_password = rand(40, 36);
            var new_key = rand(160, 36);
            var res_body = {
                'title': String
            }

            Account.update({ 'username': req.body.username }, 
                { 'key': new_key, 'password': HmacSHA256(new_password, new_key), 'email': HmacSHA256(req.body.email,new_key) }, 
                { 'upsert': false, 'multi': false },
                function(err, numUpdated) {
                    if (err) console.log(err)
                    else res_body.title = "good"
                    console.log(res_body)
                })
            res.send(new_password)
        }
    })


})/*
var AnswerSchema    = new Schema({
    text:String,
    value:Boolean

});*/
var QuestionSchema = new Schema({
    type:String,
    level:Number,
    contain:String,
    "answers":{
        "_true":String,
        "a":String,
        "b":String,
        "c":String,
        "d":String
    }

});
var TestSchema = new Schema({
    name :String,
    date_create:Date,
    date_update:Date,
    level:Number,
    type:String,
    questions:[QuestionSchema]
});

// add test

router.post('/add/test', function (req,res) {
    
    var Test = mongoose.model('Test', TestSchema, 'tests');
    var new_test = new Test(req.body);
    new_test.save(function(err, data) {
                // body...
                if (err) console.log(err);
                else res.send('register success');
            });

})
router.post('/get/test', function (req, res) {
    // body...
    var Test = mongoose.model('Test', TestSchema, 'tests');
    Test.findOne({'name':req.body.name, 'level':req.body.level}, function(err, test){
        res.send(test);
    })
})
module.exports = router;
