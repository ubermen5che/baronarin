
function selected_mining() {

	while(1) {
		web3.personal.unlockAccount(eth.accounts[0], "test1", 20000);
		web3.personal.unlockAccount(eth.accounts[1], "test2", 20000);
		web3.personal.unlockAccount(eth.accounts[2], "test3", 20000);
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
