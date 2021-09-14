typeof web3 !== "undefined"
  ? (web3 = new Web3(web3.currentProvider))
  : (web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:8545")));

if (web3.isConnected()) {
  console.log("connected");
} else {
  console.log("not connected");
  alert("not connected");
}

const contractAddress = "0x45D499FC8D8892d83af30789740784e3F5BDDe52";
const smartContract = web3.eth.contract(abi).at(contractAddress);

 var index1 = 1; //계약서 프로토콜 정보1 index
 var index2 = 1; //계약서 프로토콜 정보2 index

function init() {

  // 첫번째 계정으로 주소 설정
  $("#account").val(web3.eth.accounts[0]);
  moment.locale();
}

async function showList() {
  const table = document.getElementById("table1");
  const total = smartContract.getTotal().toString();
  
  smartContract.product().watch(async (err, res) => {
    if (!err) {
      const row = table.insertRow();
      const cell0 = row.insertCell(0);
      const cell1 = row.insertCell(1);
      const cell2 = row.insertCell(2);
      const cell3 = row.insertCell(3);
      const cell4 = row.insertCell(4);
      const cell5 = row.insertCell(5);
      const cell6 = row.insertCell(6);      
       
      const time = (await res.args.timestamp.toString()) * 1000;
      cell0.innerHTML = index1;
      cell1.innerHTML = await res.args.uniquenum;
      cell2.innerHTML = await res.args.papername;
      cell3.innerHTML = await res.args.people_size.toString();
      cell4.innerHTML = await res.args.sign_count.toString();
      cell5.innerHTML = await res.args.orig_name;
      const name = await res.args.ser_fileName();
      cell6.innerHTML = moment(time).format("YYYY-MM-DD hh:mm");
    }
    index1++;
  });


  for (let i = 0; i < total; i++) {
    const product = await smartContract.getProduct(i);    
    const toString = await product.toString();
    const strArray = toString.split(",");

    var link = '<a href=file:///home/baronarin-pc/result/' + strArray[5] + '; target="_blank">' + strArray[4] + '</a>'

    const time = strArray[6] * 1000;
    const row = table.insertRow();
    const cell0 = row.insertCell(0);
    const cell1 = row.insertCell(1);
    const cell2 = row.insertCell(2);
    const cell3 = row.insertCell(3);
    const cell4 = row.insertCell(4);
    const cell5 = row.insertCell(5);
    const cell6 = row.insertCell(6);

    cell0.innerHTML = index1;
    cell1.innerHTML = strArray[0];
    cell2.innerHTML = strArray[1];
    cell3.innerHTML = strArray[2];
    cell4.innerHTML = strArray[3];
    cell5.innerHTML = link; 
    //cell6.innerHTML = strArray[5];
    
    //cell7.style.width = "60%";
    cell6.innerHTML = moment(time).format("YYYY-MM-DD hh:mm");
    index1++;
  }
}

async function showList2() {
  const table = document.getElementById("table2");
  const total = smartContract.getTotal().toString();

    smartContract.product2().watch(async (err, res) => {

    if (!err) {
    
      const row = table.insertRow();
      const cell0 = row.insertCell(0);
      const cell1 = row.insertCell(1);
      const cell2 = row.insertCell(2);
      const cell3 = row.insertCell(3);
      const cell4 = row.insertCell(4);
      const cell5 = row.insertCell(5);
      const cell6 = row.insertCell(6);    
      const cell7 = row.insertCell(7);  

      cell0.innerHTML = index2;
      cell1.innerHTML = await res.args.people1_email;
      cell2.innerHTML = await res.args.people1_time;
      cell3.innerHTML = await res.args.people2_email;;
      cell4.innerHTML = await res.args.people2_time;
      cell5.innerHTML = await res.args.people3_email;
      cell6.innerHTML = await res.args.people3_time;
      cell7.innerHTML = await res.args.result_hash;
    }
    
    index2++;
  });
  

  for (let i = 0; i < total; i++) {

    const product2 = await smartContract.getProduct2(i);    
    const toString2 = await product2.toString();
    const strArray2 = toString2.split(",");

     const row = table.insertRow();
      const cell0 = row.insertCell(0);
      const cell1 = row.insertCell(1);
      const cell2 = row.insertCell(2);
      const cell3 = row.insertCell(3);
      const cell4 = row.insertCell(4);
      const cell5 = row.insertCell(5);
      const cell6 = row.insertCell(6);    
      const cell7 = row.insertCell(7);  
    
    cell0.innerHTML = index2;
    cell1.innerHTML = strArray2[0];
    cell2.innerHTML = strArray2[1];
    cell3.innerHTML = strArray2[2];
    cell4.innerHTML = strArray2[3];
    cell5.innerHTML = strArray2[4]; 
    cell6.innerHTML = strArray2[5];
    cell7.innerHTML = strArray2[6];
    index2++;
  }

}

function addProduct1() {
    smartContract.addProduct(
    $("#uniquenum").val(),
    $("#papername").val(),
    $("#people_size").val(),
    $("#sign_count").val(),
    $("#orig_name").val(),
    $("#ser_fileName").val(),       
    { from: $("#account").val(), gas: 3000000 },
    (err, result) => {
     //if (!err) alert("트랜잭션이 성공적으로 전송되었습니다.\n" + result);
    }
  );
}

function addProduct2() {

  smartContract.addProduct2(
    $("#people1_email").val(),
    $("#people1_time").val(),
    $("#people2_email").val(),
    $("#people2_time").val(),
    $("#people3_email").val(),
    $("#people3_time").val(),       
    $("#result_hash").val(),          
    { from: $("#account").val(), gas: 3000000 },
    (err, result) => {
     // if (!err) alert("트랜잭션이 성공적으로 전송되었습니다.\n" + result);

    }
  );
}




$(function() {
  init();
  showList();
  showList2();
});

