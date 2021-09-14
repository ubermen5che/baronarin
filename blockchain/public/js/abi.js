const abi = [
  {
    constant: true,
    inputs: [],
    name: "getTotal",
    outputs: [
      {
        name: "",
        type: "uint256"
      }
    ],
    payable: false,
    stateMutability: "view",
    type: "function"
  },
  {
    constant: true,
    inputs: [
      {
        name: "",
        type: "uint256"
      }
    ],
    name: "products",
    outputs: [
      {
        name: "uniquenum",
        type: "string"
      },
      {
        name: "papername",
        type: "string"
      },
      {
        name: "people_size",
        type: "uint256"
      },
      {
        name: "sign_count",
        type: "uint256"
      },
      {
      	name: "orig_name",
      	type: "string"
      },
      {
        name: "ser_fileName",
        type: "string"
      },
      {
        name: "timestamp",
        type: "uint256"
      }
    ],
    payable: false,
    stateMutability: "view",
    type: "function"
  },
  {
    constant: true,
    inputs: [
      {
        name: "",
        type: "uint256"

      }
    ],
    name: "products2",
    outputs: [
      {
        name: "people1_email",
        type: "string"
      },
      {
        name: "people1_time",
        type: "string"
      },
      {
        name: "people2_email",
        type: "string"
      },
      {
        name: "people2_time",
        type: "string"
      },
      {
      	name: "people3_email",
      	type: "string"
      },
      {
        name: "people3_time",
        type: "string"
      },
      {
        name: "result_hash",
        type: "string"
      }
    ],
    payable: false,
    stateMutability: "view",
    type: "function"
  },
  {
    constant: true,
    inputs: [
      {
        name: "_idx",
        type: "uint256"
      }
    ],
    name: "getProduct",
    outputs: [
      {
        name: "",
        type: "string"
      },
      {
        name: "",
        type: "string"
      },
      {
        name: "",
        type: "uint256"
      },
      {
        name: "",
        type: "uint256"
      },
      {
        name: "",
        type: "string"
      },
      {
        name: "",
        type: "string"
      },
      {
        name: "",
        type: "uint256"
      }
    ],
    payable: false,
    stateMutability: "view",
    type: "function"
  },
    {
    constant: true,
    inputs: [
      {
        name: "_idx",
        type: "uint256"
      }
    ],
    name: "getProduct2",
    outputs: [
      {
        name: "",
        type: "string"
      },
      {
        name: "",
        type: "string"
      },
      {
        name: "",
        type: "string"
      },
      {
        name: "",
        type: "string"
      },
      {
        name: "",
        type: "string"
      },
      {
        name: "",

        type: "string"
      },
      {
        name: "",

        type: "string"
      }
    ],
    payable: false,
    stateMutability: "view",
    type: "function"
  },
  {
    constant: false,
    inputs: [
      {
        name: "_uniquenum",
        type: "string"
      },
      {
        name: "_papername",
        type: "string"
      },
      {
        name: "_people_size",
        type: "uint256"
      },
      {
        name: "_sign_count",
        type: "uint256"
      },
      {
      	name: "_orig_name",
      	type: "string"
      },
      {
      	name: "_ser_fileName",
      	type: "string"
      }
    ],
    name: "addProduct",
    outputs: [],
    payable: false,
    stateMutability: "nonpayable",
    type: "function"
  },
    {
    constant: false,
    inputs: [
      {
        name: "_people1_email",
        type: "string"
      },
      {
        name: "_people1_time",
        type: "string"
      },
      {
        name: "_people2_email",
        type: "string"
      },
      {
        name: "_people2_time",
        type: "string"
      },      
      {
        name: "_people3_email",
        type: "string"
      },
      {
        name: "_people3_time",
        type: "string"
      },
      {
        name: "_result_hash",
        type: "string"
      }
    ],
    name: "addProduct2",
    outputs: [],
    payable: false,
    stateMutability: "nonpayable",
    type: "function"
  },
  {
    anonymous: false,
    inputs: [
      {
        indexed: false,
        name: "uniquenum",
        type: "string"
      },
      {
        indexed: false,
        name: "papername",
        type: "string"
      },
      {
        indexed: false,
        name: "people_size",
        type: "uint256"
      },
      {
        indexed: false,
        name: "sign_count",
        type: "uint256"
      },
      {
        indexed: false,
        name: "orig_name",
        type: "string"
      },
      {
        indexed: false,
        name: "ser_fileName",
        type: "string"
      },
      {
        indexed: false,
        name: "timestamp",
        type: "uint256"
      }
    ],
    name: "product",
    type: "event"
  },
    {
    anonymous: false,
    inputs: [
      {
        indexed: false,
        name: "people1_email",
        type: "string"
      },
      {
        indexed: false,
        name: "people1_time",
        type: "string"
      },
      {
        indexed: false,
        name: "people2_email",
        type: "string"
      },
      {
        indexed: false,
        name: "people2_time",
        type: "string"
      },
      {
        indexed: false,
        name: "people3_email",
        type: "string"
      },
      {
        indexed: false,
        name: "people3_time",
        type: "string"
      },
      {
        indexed: false,
        name: "result_hash",
        type: "string"
      }
    ],
    name: "product2",
    type: "event"
  }
];
