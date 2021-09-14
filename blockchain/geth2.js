
function selected_mining() {

	while(1) {

		miner.start();
		sleep(3000);
		miner.stop();
		sleep(600000);
	}

}

function sleep(num){
	var now = new Date();
	var stop = now.getTime() + num;
	while(true) {
		now = new Date();
		if(now.getTime() > stop) return;
	}
}
