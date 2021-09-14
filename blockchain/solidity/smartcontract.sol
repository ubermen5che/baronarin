pragma solidity >=0.4.22 < 0.7.0;

contract ProductContract {
    uint total;
    struct myStruct {
        string uniquenum;
        string papername;
        uint people_size;
        uint sign_count;        
        string orig_name;
        uint file_size;        
        uint timestamp;
    }
    
    struct myStruct2 {
        string people1_email;
        string people1_time;
        string people2_email;
        string people2_time;        
        string people3_email;
        string people3_time;        
    }

    event product (
        string uniquenum,
        string papername,
        uint people_size,
        uint sign_count,       
        string orig_name,
        uint file_size,
        uint timestamp
    );
    
    event product2 (
        string people1_email,
        string people1_time,
        string people2_email,
        string people2_time,  
        string people3_email,
        string people3_time
    );

    myStruct[] public products;
    myStruct2[] public products2;

    /* 사용자가 입력한 제품을 등록 */
    function addProduct (string memory _uniquenum, string memory _papername, uint _people_size, uint _sign_count, string memory _orig_name, uint _file_size) public {
        products.push(myStruct(_uniquenum, _papername, _people_size, _sign_count, _orig_name, _file_size, block.timestamp));
        total++;
        emit product(_uniquenum, _papername, _people_size, _sign_count, _orig_name, _file_size, block.timestamp);
    }
    
    /* 사용자가 입력한 제품을 등록2 */
    function addProduct2 (string memory _people1_email, string memory _people1_time, string memory _people2_email, string memory _people2_time, string memory _people3_email, string memory _people3_time) public {
        products2.push(myStruct2(_people1_email, _people1_time, _people2_email, _people2_time, _people3_email, _people3_time));
        total++;
        emit product2(_people1_email, _people1_time, _people2_email, _people2_time, _people3_email, _people3_time);
    }

    /* 번호에 해당하는 제품의 이름을 리턴 */
    function getProduct(uint _idx) public view returns (string memory, string memory, uint, uint, string memory, uint, uint) {
        return (products[_idx].uniquenum, products[_idx].papername, products[_idx].people_size, products[_idx].sign_count, products[_idx].orig_name, products[_idx].file_size, products[_idx].timestamp);
    }
    
    /* 번호에 해당하는 제품의 이름을 리턴 */
    function getProduct2(uint _idx) public view returns (string memory, string memory, string memory, string memory, string memory, string memory) {
        return (products2[_idx].people1_email, products2[_idx].people1_time, products2[_idx].people2_email, products2[_idx].people2_time, products2[_idx].people3_email, products2[_idx].people3_time);
    }

    function getTotal() public view returns (uint) {
        return total;
    }
}
