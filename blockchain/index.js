const express = require('express')
const mysql = require('mysql2');

var connection = mysql.createConnection ({
	host	:	'localhost',
	user	:	'cos',
	password:	'cos1234',
	database:	'security'
});

connection.connect();

const app = new express()
const ejs = require('ejs')
app.set('view engine', 'ejs')

app.use(express.static('public'))

var count = 0;

app.get('/', async (req, res) =>{
	res.redirect('/data');
})

app.get('/start', async (req, res) =>{
	connection.query('SELECT * FROM Article', function (error, results, fields) {
		count = results.length;	
	});
	res.redirect('/');
})

app.get('/data', function(req, res) {

var input= ["", "", "", "", "", ""];
var input2= ["", "", "", "", "", "", ""];
var token = 0;
	connection.query('SELECT * FROM Article', function (error, results, fields) {
	
	if(count < results.length) {

		for (var i = count; i<results.length; i++) {
	
			if(results[i] == '' || results[i] == undefined)
				break;

			input[0] = results[i].uniquenum;

			input[1] = results[i].papername;

			input[2] = results[i].people_size;

			input[3] = results[i].sign_count;

			input[4] = results[i].orig_name;

			input[5] = results[i].ser_fileName;
			
			if(input[2] != input[3]) 
				continue;
				
			input2[0] = results[i].people1_email;
			
			input2[1] = results[i].people1_time;
			
			input2[2] = results[i].people2_email;
			
			input2[3] = results[i].people2_time;
			
			input2[4] = results[i].people3_email;
			
			input2[5] = results[i].people3_time;
			
			input2[6] = results[i].result_hash;
		

			res.render('data', {'block' : input, 'block2' : input2}, function(err, html) {
				if(!err) {
					res.end(html);
					sleep(1000);
				}
				else return console.error(err);
				

			});
		}
		if(input[2] == input[3]) {
			console.log(new Date() + '==> change success!');
			// count = results.length;
			count += 1;
		}
		else {
			console.log(new Date() + '==> not matching!');
			res.render('index');
		}

	}
	else {
		console.log(new Date() +'==> nothing change!');
		res.render('index');
	}

	});

});

function sleep(num){
	var now = new Date();
	var stop = now.getTime() + num;
	while(true) {
		now = new Date();
		if(now.getTime() > stop) return;
	}
}

app.listen(4000, () => { console.log('App listening on port ' + 4000) })

