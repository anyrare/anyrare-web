(ns app.abi
  (:require
   [app.env :as env]))

(def member-abi
  [{:inputs [{:internalType "address", :name "root", :type "address"}]
    :stateMutability "nonpayable"
    :type "constructor"}
   {:inputs [{:internalType "address", :name "addr", :type "address"}]
    :name "getReferral"
    :outputs [{:internalType "address", :name "", :type "address"}]
    :stateMutability "view"
    :type "function"}
   {:inputs
    [{:internalType "address", :name "account", :type "address"}]
    :name "isMember"
    :outputs [{:internalType "bool", :name "", :type "bool"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "address", :name "", :type "address"}]
    :name "members"
    :outputs
    [{:internalType "address", :name "referral", :type "address"}]
    :stateMutability "view"
    :type "function"}
   {:inputs
    [{:internalType "address", :name "addr", :type "address"}
     {:internalType "address", :name "referral", :type "address"}]
    :name "setMember"
    :outputs []
    :stateMutability "nonpayable"
    :type "function"}])

(def governance-abi
  [{:inputs [], :stateMutability "nonpayable", :type "constructor"}
   {:inputs [{:internalType "address", :name "", :type "address"}]
    :name "auditors"
    :outputs
    [{:internalType "bool", :name "approve", :type "bool"}
     {:internalType "string", :name "dataURI", :type "string"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "address", :name "", :type "address"}]
    :name "custodians"
    :outputs
    [{:internalType "bool", :name "approve", :type "bool"}
     {:internalType "string", :name "dataURI", :type "string"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "getARATokenContract"
    :outputs [{:internalType "address", :name "", :type "address"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "getBancorFormulaContract"
    :outputs [{:internalType "address", :name "", :type "address"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "getCollectionFactoryContract"
    :outputs [{:internalType "address", :name "", :type "address"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "getCollectionUtilsContract"
    :outputs [{:internalType "address", :name "", :type "address"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "getManagementFundContract"
    :outputs [{:internalType "address", :name "", :type "address"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "uint16", :name "index", :type "uint16"}]
    :name "getManager"
    :outputs
    [{:components
      [{:internalType "address", :name "addr", :type "address"}
       {:internalType "uint256", :name "controlWeight", :type "uint256"}
       {:internalType "string", :name "dataURI", :type "string"}]
      :internalType "struct Governance.Manager"
      :name "manager"
      :type "tuple"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "address", :name "addr", :type "address"}]
    :name "getManagerByAddress"
    :outputs
    [{:components
      [{:internalType "address", :name "addr", :type "address"}
       {:internalType "uint256", :name "controlWeight", :type "uint256"}
       {:internalType "string", :name "dataURI", :type "string"}]
      :internalType "struct Governance.Manager"
      :name "manager"
      :type "tuple"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "getManagerMaxControlWeight"
    :outputs [{:internalType "uint256", :name "", :type "uint256"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "getMemberContract"
    :outputs [{:internalType "address", :name "", :type "address"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "getNFTFactoryContract"
    :outputs [{:internalType "address", :name "", :type "address"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "getNFTUtilsContract"
    :outputs [{:internalType "address", :name "", :type "address"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "uint16", :name "index", :type "uint16"}]
    :name "getOperation"
    :outputs
    [{:components
      [{:internalType "address", :name "addr", :type "address"}
       {:internalType "uint256", :name "controlWeight", :type "uint256"}
       {:internalType "string", :name "dataURI", :type "string"}]
      :internalType "struct Governance.Operation"
      :name "operation"
      :type "tuple"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "address", :name "addr", :type "address"}]
    :name "getOperationByAddress"
    :outputs
    [{:components
      [{:internalType "address", :name "addr", :type "address"}
       {:internalType "uint256", :name "controlWeight", :type "uint256"}
       {:internalType "string", :name "dataURI", :type "string"}]
      :internalType "struct Governance.Operation"
      :name "operation"
      :type "tuple"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "getOperationMaxControlWeight"
    :outputs [{:internalType "uint256", :name "", :type "uint256"}]
    :stateMutability "view"
    :type "function"}
   {:inputs
    [{:internalType "string", :name "policyName", :type "string"}]
    :name "getPolicy"
    :outputs
    [{:components
      [{:internalType "uint256", :name "policyWeight", :type "uint256"}
       {:internalType "uint256", :name "maxWeight", :type "uint256"}
       {:internalType "uint32", :name "voteDuration", :type "uint32"}
       {:internalType "uint32"
        :name "effectiveDuration"
        :type "uint32"}
       {:internalType "uint256"
        :name "minWeightOpenVote"
        :type "uint256"}
       {:internalType "uint256"
        :name "minWeightValidVote"
        :type "uint256"}
       {:internalType "uint256"
        :name "minWeightApproveVote"
        :type "uint256"}
       {:internalType "uint256", :name "policyValue", :type "uint256"}
       {:internalType "uint8", :name "decider", :type "uint8"}
       {:internalType "bool", :name "exists", :type "bool"}
       {:internalType "bool", :name "openVote", :type "bool"}]
      :internalType "struct Governance.Policy"
      :name "policy"
      :type "tuple"}]
    :stateMutability "view"
    :type "function"}
   {:inputs
    [{:internalType "bytes32", :name "policyIndex", :type "bytes32"}]
    :name "getPolicyByIndex"
    :outputs
    [{:components
      [{:internalType "uint256", :name "policyWeight", :type "uint256"}
       {:internalType "uint256", :name "maxWeight", :type "uint256"}
       {:internalType "uint32", :name "voteDuration", :type "uint32"}
       {:internalType "uint32"
        :name "effectiveDuration"
        :type "uint32"}
       {:internalType "uint256"
        :name "minWeightOpenVote"
        :type "uint256"}
       {:internalType "uint256"
        :name "minWeightValidVote"
        :type "uint256"}
       {:internalType "uint256"
        :name "minWeightApproveVote"
        :type "uint256"}
       {:internalType "uint256", :name "policyValue", :type "uint256"}
       {:internalType "uint8", :name "decider", :type "uint8"}
       {:internalType "bool", :name "exists", :type "bool"}
       {:internalType "bool", :name "openVote", :type "bool"}]
      :internalType "struct Governance.Policy"
      :name "policy"
      :type "tuple"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "getProposalContract"
    :outputs [{:internalType "address", :name "", :type "address"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "getTotalManager"
    :outputs [{:internalType "uint16", :name "", :type "uint16"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "getTotalOperation"
    :outputs [{:internalType "uint16", :name "", :type "uint16"}]
    :stateMutability "view"
    :type "function"}
   {:inputs
    [{:internalType "address", :name "_memberContract", :type "address"}
     {:internalType "address"
      :name "_araTokenContract"
      :type "address"}
     {:internalType "address"
      :name "_bancorFormulaContract"
      :type "address"}
     {:internalType "address"
      :name "_proposalContract"
      :type "address"}
     {:internalType "address"
      :name "_nftFactoryContract"
      :type "address"}
     {:internalType "address"
      :name "_nftUtilsContract"
      :type "address"}
     {:internalType "address"
      :name "_collectionFactoryContract"
      :type "address"}
     {:internalType "address"
      :name "_collectionUtilsContract"
      :type "address"}
     {:internalType "address"
      :name "_managementFundContract"
      :type "address"}]
    :name "initContractAddress"
    :outputs []
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs
    [{:internalType "address", :name "_manager", :type "address"}
     {:internalType "address", :name "_operation", :type "address"}
     {:internalType "address", :name "_auditor", :type "address"}
     {:internalType "address", :name "_custodian", :type "address"}
     {:internalType "uint16", :name "_totalPolicy", :type "uint16"}
     {:components
      [{:internalType "string", :name "policyName", :type "string"}
       {:internalType "uint256", :name "policyWeight", :type "uint256"}
       {:internalType "uint256", :name "maxWeight", :type "uint256"}
       {:internalType "uint32", :name "voteDuration", :type "uint32"}
       {:internalType "uint32"
        :name "effectiveDuration"
        :type "uint32"}
       {:internalType "uint256"
        :name "minWeightOpenVote"
        :type "uint256"}
       {:internalType "uint256"
        :name "minWeightValidVote"
        :type "uint256"}
       {:internalType "uint256"
        :name "minWeightApproveVote"
        :type "uint256"}
       {:internalType "uint256", :name "policyValue", :type "uint256"}
       {:internalType "uint8", :name "decider", :type "uint8"}]
      :internalType "struct Governance.InitPolicy[]"
      :name "_policies"
      :type "tuple[]"}]
    :name "initPolicy"
    :outputs []
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs [{:internalType "address", :name "addr", :type "address"}]
    :name "isAuditor"
    :outputs [{:internalType "bool", :name "", :type "bool"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "address", :name "addr", :type "address"}]
    :name "isCustodian"
    :outputs [{:internalType "bool", :name "", :type "bool"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "address", :name "addr", :type "address"}]
    :name "isManager"
    :outputs [{:internalType "bool", :name "", :type "bool"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "address", :name "addr", :type "address"}]
    :name "isOperation"
    :outputs [{:internalType "bool", :name "", :type "bool"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "managerMaxControlWeight"
    :outputs [{:internalType "uint256", :name "", :type "uint256"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "uint16", :name "", :type "uint16"}]
    :name "managers"
    :outputs
    [{:internalType "address", :name "addr", :type "address"}
     {:internalType "uint256", :name "controlWeight", :type "uint256"}
     {:internalType "string", :name "dataURI", :type "string"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "address", :name "", :type "address"}]
    :name "managersAddress"
    :outputs [{:internalType "uint16", :name "", :type "uint16"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "operationMaxControlWeight"
    :outputs [{:internalType "uint256", :name "", :type "uint256"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "uint16", :name "", :type "uint16"}]
    :name "operations"
    :outputs
    [{:internalType "address", :name "addr", :type "address"}
     {:internalType "uint256", :name "controlWeight", :type "uint256"}
     {:internalType "string", :name "dataURI", :type "string"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "address", :name "", :type "address"}]
    :name "operationsAddress"
    :outputs [{:internalType "uint16", :name "", :type "uint16"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "bytes32", :name "", :type "bytes32"}]
    :name "policies"
    :outputs
    [{:internalType "uint256", :name "policyWeight", :type "uint256"}
     {:internalType "uint256", :name "maxWeight", :type "uint256"}
     {:internalType "uint32", :name "voteDuration", :type "uint32"}
     {:internalType "uint32", :name "effectiveDuration", :type "uint32"}
     {:internalType "uint256"
      :name "minWeightOpenVote"
      :type "uint256"}
     {:internalType "uint256"
      :name "minWeightValidVote"
      :type "uint256"}
     {:internalType "uint256"
      :name "minWeightApproveVote"
      :type "uint256"}
     {:internalType "uint256", :name "policyValue", :type "uint256"}
     {:internalType "uint8", :name "decider", :type "uint8"}
     {:internalType "bool", :name "exists", :type "bool"}
     {:internalType "bool", :name "openVote", :type "bool"}]
    :stateMutability "view"
    :type "function"}
   {:inputs
    [{:internalType "address", :name "addr", :type "address"}
     {:internalType "bool", :name "approve", :type "bool"}
     {:internalType "string", :name "dataURI", :type "string"}]
    :name "setAuditorByProposal"
    :outputs []
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs
    [{:internalType "address", :name "addr", :type "address"}
     {:internalType "bool", :name "approve", :type "bool"}
     {:internalType "string", :name "dataURI", :type "string"}]
    :name "setCustodianByProposal"
    :outputs []
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs
    [{:internalType "uint16", :name "_totalManager", :type "uint16"}
     {:internalType "uint16", :name "managerIndex", :type "uint16"}
     {:internalType "address", :name "addr", :type "address"}
     {:internalType "uint256", :name "controlWeight", :type "uint256"}
     {:internalType "uint256", :name "maxWeight", :type "uint256"}
     {:internalType "string", :name "dataURI", :type "string"}]
    :name "setManagerAtIndexByProposal"
    :outputs []
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs
    [{:internalType "uint16", :name "_totalOperation", :type "uint16"}
     {:internalType "uint16", :name "operationIndex", :type "uint16"}
     {:internalType "address", :name "addr", :type "address"}
     {:internalType "uint256", :name "controlWeight", :type "uint256"}
     {:internalType "uint256", :name "maxWeight", :type "uint256"}
     {:internalType "string", :name "dataURI", :type "string"}]
    :name "setOperationAtIndexByProposal"
    :outputs []
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs
    [{:internalType "bytes32", :name "policyIndex", :type "bytes32"}
     {:internalType "uint256", :name "policyWeight", :type "uint256"}
     {:internalType "uint256", :name "maxWeight", :type "uint256"}
     {:internalType "uint32", :name "voteDuration", :type "uint32"}
     {:internalType "uint256"
      :name "minWeightOpenVote"
      :type "uint256"}
     {:internalType "uint256"
      :name "minWeightValidVote"
      :type "uint256"}
     {:internalType "uint256"
      :name "minWeightApproveVote"
      :type "uint256"}
     {:internalType "uint256", :name "policyValue", :type "uint256"}
     {:internalType "uint8", :name "decider", :type "uint8"}]
    :name "setPolicyByProposal"
    :outputs []
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs [{:internalType "string", :name "str", :type "string"}]
    :name "stringToBytes32"
    :outputs [{:internalType "bytes32", :name "", :type "bytes32"}]
    :stateMutability "pure"
    :type "function"}
   {:inputs []
    :name "totalManager"
    :outputs [{:internalType "uint16", :name "", :type "uint16"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "totalOperation"
    :outputs [{:internalType "uint16", :name "", :type "uint16"}]
    :stateMutability "view"
    :type "function"}])

(def ara-token-abi
  [{:inputs
    [{:internalType "address"
      :name "_governanceContract"
      :type "address"}
     {:internalType "address"
      :name "_bancorFormulaContract"
      :type "address"}
     {:internalType "string", :name "_name", :type "string"}
     {:internalType "string", :name "_symbol", :type "string"}
     {:internalType "address", :name "_collateralToken", :type "address"}
     {:internalType "uint256", :name "initialAmount", :type "uint256"}]
    :stateMutability "nonpayable"
    :type "constructor"}
   {:anonymous false
    :inputs
    [{:indexed true
      :internalType "address"
      :name "owner"
      :type "address"}
     {:indexed true
      :internalType "address"
      :name "spender"
      :type "address"}
     {:indexed false
      :internalType "uint256"
      :name "value"
      :type "uint256"}]
    :name "Approval"
    :type "event"}
   {:anonymous false
    :inputs
    [{:indexed true
      :internalType "address"
      :name "from"
      :type "address"}
     {:indexed true
      :internalType "address"
      :name "to"
      :type "address"}
     {:indexed false
      :internalType "uint256"
      :name "value"
      :type "uint256"}]
    :name "Transfer"
    :type "event"}
   {:inputs
    [{:internalType "address", :name "owner", :type "address"}
     {:internalType "address", :name "spender", :type "address"}]
    :name "allowance"
    :outputs [{:internalType "uint256", :name "", :type "uint256"}]
    :stateMutability "view"
    :type "function"}
   {:inputs
    [{:internalType "address", :name "spender", :type "address"}
     {:internalType "uint256", :name "amount", :type "uint256"}]
    :name "approve"
    :outputs [{:internalType "bool", :name "", :type "bool"}]
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs
    [{:internalType "address", :name "account", :type "address"}]
    :name "balanceOf"
    :outputs [{:internalType "uint256", :name "", :type "uint256"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "uint256", :name "amount", :type "uint256"}]
    :name "burn"
    :outputs []
    :stateMutability "payable"
    :type "function"}
   {:inputs [{:internalType "uint256", :name "amount", :type "uint256"}]
    :name "calculateFundCost"
    :outputs [{:internalType "uint256", :name "", :type "uint256"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "uint256", :name "amount", :type "uint256"}]
    :name "calculateLiquidateCost"
    :outputs [{:internalType "uint256", :name "", :type "uint256"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "uint256", :name "amount", :type "uint256"}]
    :name "calculatePurchaseReturn"
    :outputs [{:internalType "uint256", :name "", :type "uint256"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "uint256", :name "amount", :type "uint256"}]
    :name "calculateSaleReturn"
    :outputs [{:internalType "uint256", :name "", :type "uint256"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "currentPrice"
    :outputs [{:internalType "uint256", :name "", :type "uint256"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "currentTotalValue"
    :outputs [{:internalType "uint256", :name "", :type "uint256"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "decimals"
    :outputs [{:internalType "uint8", :name "", :type "uint8"}]
    :stateMutability "view"
    :type "function"}
   {:inputs
    [{:internalType "address", :name "spender", :type "address"}
     {:internalType "uint256"
      :name "subtractedValue"
      :type "uint256"}]
    :name "decreaseAllowance"
    :outputs [{:internalType "bool", :name "", :type "bool"}]
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs []
    :name "getManagementFundValue"
    :outputs [{:internalType "uint256", :name "", :type "uint256"}]
    :stateMutability "view"
    :type "function"}
   {:inputs
    [{:internalType "address", :name "spender", :type "address"}
     {:internalType "uint256", :name "addedValue", :type "uint256"}]
    :name "increaseAllowance"
    :outputs [{:internalType "bool", :name "", :type "bool"}]
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs []
    :name "managementFundValue"
    :outputs [{:internalType "uint256", :name "", :type "uint256"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "uint256", :name "amount", :type "uint256"}]
    :name "mint"
    :outputs []
    :stateMutability "payable"
    :type "function"}
   {:inputs []
    :name "name"
    :outputs [{:internalType "string", :name "", :type "string"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "symbol"
    :outputs [{:internalType "string", :name "", :type "string"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "totalFreeFloatSupply"
    :outputs [{:internalType "uint256", :name "", :type "uint256"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "totalSupply"
    :outputs [{:internalType "uint256", :name "", :type "uint256"}]
    :stateMutability "view"
    :type "function"}
   {:inputs
    [{:internalType "address", :name "recipient", :type "address"}
     {:internalType "uint256", :name "amount", :type "uint256"}]
    :name "transfer"
    :outputs [{:internalType "bool", :name "", :type "bool"}]
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs
    [{:internalType "address", :name "sender", :type "address"}
     {:internalType "address", :name "recipient", :type "address"}
     {:internalType "uint256", :name "amount", :type "uint256"}]
    :name "transferFrom"
    :outputs [{:internalType "bool", :name "", :type "bool"}]
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs [{:internalType "uint256", :name "amount", :type "uint256"}]
    :name "withdraw"
    :outputs []
    :stateMutability "payable"
    :type "function"}])

(def proposal-abi
  [{:inputs
    [{:internalType "address"
      :name "_governanceContract"
      :type "address"}]
    :stateMutability "nonpayable"
    :type "constructor"}
   {:inputs
    [{:internalType "uint32", :name "proposalId", :type "uint32"}]
    :name "applyListProposal"
    :outputs []
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs
    [{:internalType "string", :name "policyName", :type "string"}]
    :name "applyPolicyProposal"
    :outputs []
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs
    [{:internalType "uint32", :name "proposalId", :type "uint32"}]
    :name "countVoteListProposal"
    :outputs []
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs
    [{:internalType "string", :name "policyName", :type "string"}]
    :name "countVotePolicyProposal"
    :outputs []
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs []
    :name "getCurrentListProposalId"
    :outputs [{:internalType "uint256", :name "", :type "uint256"}]
    :stateMutability "view"
    :type "function"}
   {:inputs
    [{:internalType "string", :name "policyName", :type "string"}]
    :name "getCurrentPolicyProposal"
    :outputs
    [{:components
      [{:internalType "bytes32", :name "policyIndex", :type "bytes32"}
       {:internalType "bool", :name "openVote", :type "bool"}
       {:internalType "bool", :name "countVote", :type "bool"}
       {:internalType "bool", :name "applyProposal", :type "bool"}
       {:internalType "uint256"
        :name "closeVoteTimestamp"
        :type "uint256"}
       {:internalType "uint256", :name "policyWeight", :type "uint256"}
       {:internalType "uint256", :name "maxWeight", :type "uint256"}
       {:internalType "uint32", :name "voteDuration", :type "uint32"}
       {:internalType "uint32"
        :name "effectiveDuration"
        :type "uint32"}
       {:internalType "uint256"
        :name "minWeightOpenVote"
        :type "uint256"}
       {:internalType "uint256"
        :name "minWeightValidVote"
        :type "uint256"}
       {:internalType "uint256"
        :name "minWeightApproveVote"
        :type "uint256"}
       {:internalType "uint256", :name "policyValue", :type "uint256"}
       {:internalType "uint8", :name "decider", :type "uint8"}
       {:internalType "uint8", :name "voteDecider", :type "uint8"}
       {:internalType "uint256", :name "totalVoteToken", :type "uint256"}
       {:internalType "uint256"
        :name "totalApproveToken"
        :type "uint256"}
       {:internalType "uint256"
        :name "totalSupplyToken"
        :type "uint256"}
       {:internalType "bool", :name "voteResult", :type "bool"}
       {:internalType "uint256"
        :name "processResultTimestamp"
        :type "uint256"}
       {:internalType "uint32", :name "totalVoter", :type "uint32"}]
      :internalType "struct ProposalDataType.PolicyProposalInfo"
      :name "policyProposalInfo"
      :type "tuple"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "getCurrentPolicyProposalId"
    :outputs [{:internalType "uint256", :name "", :type "uint256"}]
    :stateMutability "view"
    :type "function"}
   {:inputs []
    :name "listProposalId"
    :outputs [{:internalType "uint32", :name "", :type "uint32"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "uint32", :name "", :type "uint32"}]
    :name "listProposals"
    :outputs
    [{:internalType "bool", :name "exists", :type "bool"}
     {:components
      [{:internalType "bytes32", :name "policyIndex", :type "bytes32"}
       {:internalType "bool", :name "openVote", :type "bool"}
       {:internalType "bool", :name "countVote", :type "bool"}
       {:internalType "bool", :name "applyProposal", :type "bool"}
       {:internalType "bool", :name "voteValid", :type "bool"}
       {:internalType "bool", :name "voteApprove", :type "bool"}
       {:internalType "uint256", :name "maxWeight", :type "uint256"}
       {:internalType "uint256"
        :name "closeVoteTimestamp"
        :type "uint256"}
       {:internalType "uint256", :name "totalVoteToken", :type "uint256"}
       {:internalType "uint256"
        :name "totalApproveToken"
        :type "uint256"}
       {:internalType "uint256"
        :name "totalSupplyToken"
        :type "uint256"}
       {:internalType "uint256"
        :name "processResultTimestamp"
        :type "uint256"}
       {:internalType "uint32", :name "totalVoter", :type "uint32"}
       {:internalType "uint16", :name "totalList", :type "uint16"}]
      :internalType "struct ProposalDataType.ListProposalInfo"
      :name "info"
      :type "tuple"}]
    :stateMutability "view"
    :type "function"}
   {:inputs
    [{:internalType "string", :name "policyName", :type "string"}
     {:internalType "uint256", :name "maxWeight", :type "uint256"}
     {:components
      [{:internalType "address", :name "addr", :type "address"}
       {:internalType "uint256", :name "controlWeight", :type "uint256"}
       {:internalType "string", :name "dataURI", :type "string"}]
      :internalType "struct ProposalDataType.ListInfo[]"
      :name "lists"
      :type "tuple[]"}
     {:internalType "uint16", :name "totalList", :type "uint16"}]
    :name "openListProposal"
    :outputs []
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs
    [{:internalType "string", :name "policyName", :type "string"}
     {:internalType "uint256", :name "policyWeight", :type "uint256"}
     {:internalType "uint256", :name "maxWeight", :type "uint256"}
     {:internalType "uint32", :name "voteDuration", :type "uint32"}
     {:internalType "uint32", :name "effectiveDuration", :type "uint32"}
     {:internalType "uint256"
      :name "minWeightOpenVote"
      :type "uint256"}
     {:internalType "uint256"
      :name "minWeightValidVote"
      :type "uint256"}
     {:internalType "uint256"
      :name "minWeightApproveVote"
      :type "uint256"}
     {:internalType "uint256", :name "policyValue", :type "uint256"}
     {:internalType "uint8", :name "decider", :type "uint8"}]
    :name "openPolicyProposal"
    :outputs []
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs []
    :name "policyProposalId"
    :outputs [{:internalType "uint32", :name "", :type "uint32"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "bytes32", :name "", :type "bytes32"}]
    :name "policyProposalIndexes"
    :outputs
    [{:internalType "bytes32", :name "policyIndex", :type "bytes32"}
     {:internalType "uint32", :name "id", :type "uint32"}
     {:internalType "bool", :name "openVote", :type "bool"}
     {:internalType "bool", :name "exists", :type "bool"}]
    :stateMutability "view"
    :type "function"}
   {:inputs [{:internalType "uint32", :name "", :type "uint32"}]
    :name "policyProposals"
    :outputs
    [{:internalType "bool", :name "exists", :type "bool"}
     {:components
      [{:internalType "bytes32", :name "policyIndex", :type "bytes32"}
       {:internalType "bool", :name "openVote", :type "bool"}
       {:internalType "bool", :name "countVote", :type "bool"}
       {:internalType "bool", :name "applyProposal", :type "bool"}
       {:internalType "uint256"
        :name "closeVoteTimestamp"
        :type "uint256"}
       {:internalType "uint256", :name "policyWeight", :type "uint256"}
       {:internalType "uint256", :name "maxWeight", :type "uint256"}
       {:internalType "uint32", :name "voteDuration", :type "uint32"}
       {:internalType "uint32"
        :name "effectiveDuration"
        :type "uint32"}
       {:internalType "uint256"
        :name "minWeightOpenVote"
        :type "uint256"}
       {:internalType "uint256"
        :name "minWeightValidVote"
        :type "uint256"}
       {:internalType "uint256"
        :name "minWeightApproveVote"
        :type "uint256"}
       {:internalType "uint256", :name "policyValue", :type "uint256"}
       {:internalType "uint8", :name "decider", :type "uint8"}
       {:internalType "uint8", :name "voteDecider", :type "uint8"}
       {:internalType "uint256", :name "totalVoteToken", :type "uint256"}
       {:internalType "uint256"
        :name "totalApproveToken"
        :type "uint256"}
       {:internalType "uint256"
        :name "totalSupplyToken"
        :type "uint256"}
       {:internalType "bool", :name "voteResult", :type "bool"}
       {:internalType "uint256"
        :name "processResultTimestamp"
        :type "uint256"}
       {:internalType "uint32", :name "totalVoter", :type "uint32"}]
      :internalType "struct ProposalDataType.PolicyProposalInfo"
      :name "info"
      :type "tuple"}]
    :stateMutability "view"
    :type "function"}
   {:inputs
    [{:internalType "uint32", :name "proposalId", :type "uint32"}
     {:internalType "bool", :name "approve", :type "bool"}]
    :name "voteListProposal"
    :outputs []
    :stateMutability "nonpayable"
    :type "function"}
   {:inputs
    [{:internalType "string", :name "policyName", :type "string"}
     {:internalType "bool", :name "approve", :type "bool"}]
    :name "votePolicyProposal"
    :outputs []
    :stateMutability "nonpayable"
    :type "function"}])

(def nft-factory-abi
  [{:inputs
  [{:internalType "address",
    :name "_governanceContract",
    :type "address"}
   {:internalType "string", :name "_name", :type "string"}
   {:internalType "string", :name "_symbol", :type "string"}],
  :stateMutability "nonpayable",
  :type "constructor"}
 {:anonymous false,
  :inputs
  [{:indexed true,
    :internalType "address",
    :name "owner",
    :type "address"}
   {:indexed true,
    :internalType "address",
    :name "approved",
    :type "address"}
   {:indexed true,
    :internalType "uint256",
    :name "tokenId",
    :type "uint256"}],
  :name "Approval",
  :type "event"}
 {:anonymous false,
  :inputs
  [{:indexed true,
    :internalType "address",
    :name "owner",
    :type "address"}
   {:indexed true,
    :internalType "address",
    :name "operator",
    :type "address"}
   {:indexed false,
    :internalType "bool",
    :name "approved",
    :type "bool"}],
  :name "ApprovalForAll",
  :type "event"}
 {:anonymous false,
  :inputs
  [{:indexed true,
    :internalType "address",
    :name "from",
    :type "address"}
   {:indexed true,
    :internalType "address",
    :name "to",
    :type "address"}
   {:indexed true,
    :internalType "uint256",
    :name "tokenId",
    :type "uint256"}],
  :name "Transfer",
  :type "event"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}],
  :name "acceptOffer",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs
  [{:internalType "address", :name "to", :type "address"}
   {:internalType "uint256", :name "tokenId", :type "uint256"}],
  :name "approve",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs [{:internalType "address", :name "owner", :type "address"}],
  :name "balanceOf",
  :outputs [{:internalType "uint256", :name "", :type "uint256"}],
  :stateMutability "view",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}
   {:internalType "uint256", :name "bidValue", :type "uint256"}
   {:internalType "uint256", :name "maxBid", :type "uint256"}],
  :name "bidAuction",
  :outputs [],
  :stateMutability "payable",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}],
  :name "buyFromBuyItNow",
  :outputs [],
  :stateMutability "payable",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}
   {:internalType "uint256", :name "value", :type "uint256"}],
  :name "changeBuyItNowPrice",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}],
  :name "closeBuyItNow",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}
   {:internalType "uint256", :name "custodianWeight", :type "uint256"}
   {:internalType "uint256",
    :name "custodianGeneralFee",
    :type "uint256"}
   {:internalType "uint256",
    :name "custodianRedeemWeight",
    :type "uint256"}],
  :name "custodianSign",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}],
  :name "getApproved",
  :outputs [{:internalType "address", :name "", :type "address"}],
  :stateMutability "view",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}],
  :name "getAuction",
  :outputs
  [{:components
    [{:internalType "uint256",
      :name "openAuctionTimestamp",
      :type "uint256"}
     {:internalType "uint256",
      :name "closeAuctionTimestamp",
      :type "uint256"}
     {:internalType "address", :name "owner", :type "address"}
     {:internalType "address", :name "bidder", :type "address"}
     {:internalType "uint256", :name "startingPrice", :type "uint256"}
     {:internalType "uint256", :name "reservePrice", :type "uint256"}
     {:internalType "uint256", :name "value", :type "uint256"}
     {:internalType "uint256", :name "maxBid", :type "uint256"}
     {:internalType "uint256", :name "maxWeight", :type "uint256"}
     {:internalType "uint256", :name "nextBidWeight", :type "uint256"}
     {:internalType "uint32", :name "totalBid", :type "uint32"}
     {:internalType "bool", :name "meetReservePrice", :type "bool"}],
    :internalType "struct NFTDataType.NFTAuction",
    :name "a",
    :type "tuple"}],
  :stateMutability "view",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}
   {:internalType "uint32", :name "bidId", :type "uint32"}],
  :name "getAuctionBid",
  :outputs
  [{:components
    [{:internalType "uint32", :name "auctionId", :type "uint32"}
     {:internalType "uint256", :name "timestamp", :type "uint256"}
     {:internalType "uint256", :name "value", :type "uint256"}
     {:internalType "address", :name "bidder", :type "address"}
     {:internalType "bool", :name "meetReservePrice", :type "bool"}
     {:internalType "bool", :name "autoRebid", :type "bool"}],
    :internalType "struct NFTDataType.NFTAuctionBid",
    :name "bid",
    :type "tuple"}],
  :stateMutability "view",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}
   {:internalType "uint32", :name "auctionId", :type "uint32"}],
  :name "getAuctionByAuctionId",
  :outputs
  [{:components
    [{:internalType "uint256",
      :name "openAuctionTimestamp",
      :type "uint256"}
     {:internalType "uint256",
      :name "closeAuctionTimestamp",
      :type "uint256"}
     {:internalType "address", :name "owner", :type "address"}
     {:internalType "address", :name "bidder", :type "address"}
     {:internalType "uint256", :name "startingPrice", :type "uint256"}
     {:internalType "uint256", :name "reservePrice", :type "uint256"}
     {:internalType "uint256", :name "value", :type "uint256"}
     {:internalType "uint256", :name "maxBid", :type "uint256"}
     {:internalType "uint256", :name "maxWeight", :type "uint256"}
     {:internalType "uint256", :name "nextBidWeight", :type "uint256"}
     {:internalType "uint32", :name "totalBid", :type "uint32"}
     {:internalType "bool", :name "meetReservePrice", :type "bool"}],
    :internalType "struct NFTDataType.NFTAuction",
    :name "a",
    :type "tuple"}],
  :stateMutability "view",
  :type "function"}
 {:inputs [],
  :name "getCurrentTokenId",
  :outputs [{:internalType "uint256", :name "", :type "uint256"}],
  :stateMutability "view",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}
   {:internalType "uint32", :name "offerId", :type "uint32"}],
  :name "getOfferBid",
  :outputs
  [{:components
    [{:internalType "uint256", :name "value", :type "uint256"}
     {:internalType "address", :name "bidder", :type "address"}
     {:internalType "uint256", :name "timestamp", :type "uint256"}],
    :internalType "struct NFTDataType.NFTOfferBid",
    :name "bid",
    :type "tuple"}],
  :stateMutability "view",
  :type "function"}
 {:inputs
  [{:internalType "address", :name "owner", :type "address"}
   {:internalType "address", :name "operator", :type "address"}],
  :name "isApprovedForAll",
  :outputs [{:internalType "bool", :name "", :type "bool"}],
  :stateMutability "view",
  :type "function"}
 {:inputs [],
  :name "m",
  :outputs
  [{:internalType "contract Member", :name "", :type "address"}],
  :stateMutability "view",
  :type "function"}
 {:inputs
  [{:internalType "address", :name "founder", :type "address"}
   {:internalType "address", :name "custodian", :type "address"}
   {:internalType "string", :name "tokenURI", :type "string"}
   {:internalType "uint256", :name "maxWeight", :type "uint256"}
   {:internalType "uint256", :name "founderWeight", :type "uint256"}
   {:internalType "uint256",
    :name "founderRedeemWeight",
    :type "uint256"}
   {:internalType "uint256",
    :name "founderGeneralFee",
    :type "uint256"}
   {:internalType "uint256", :name "auditFee", :type "uint256"}],
  :name "mint",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs [],
  :name "name",
  :outputs [{:internalType "string", :name "", :type "string"}],
  :stateMutability "view",
  :type "function"}
 {:inputs [{:internalType "uint256", :name "", :type "uint256"}],
  :name "nfts",
  :outputs
  [{:components
    [{:internalType "bool", :name "exists", :type "bool"}
     {:internalType "uint256", :name "tokenId", :type "uint256"}
     {:components
      [{:internalType "bool", :name "custodianSign", :type "bool"}
       {:internalType "bool", :name "claim", :type "bool"}
       {:internalType "bool", :name "lockInCollection", :type "bool"}
       {:internalType "bool", :name "auction", :type "bool"}
       {:internalType "bool", :name "buyItNow", :type "bool"}
       {:internalType "bool", :name "offer", :type "bool"}
       {:internalType "bool", :name "redeem", :type "bool"}
       {:internalType "bool", :name "freeze", :type "bool"}],
      :internalType "struct NFTDataType.NFTStatus",
      :name "status",
      :type "tuple"}
     {:components
      [{:internalType "address", :name "auditor", :type "address"}
       {:internalType "address", :name "custodian", :type "address"}
       {:internalType "address", :name "founder", :type "address"}
       {:internalType "address", :name "owner", :type "address"}],
      :internalType "struct NFTDataType.NFTAddress",
      :name "addr",
      :type "tuple"}
     {:components
      [{:internalType "uint256", :name "maxWeight", :type "uint256"}
       {:internalType "uint256",
        :name "founderWeight",
        :type "uint256"}
       {:internalType "uint256",
        :name "founderGeneralFee",
        :type "uint256"}
       {:internalType "uint256",
        :name "founderRedeemWeight",
        :type "uint256"}
       {:internalType "uint256",
        :name "custodianWeight",
        :type "uint256"}
       {:internalType "uint256",
        :name "custodianGeneralFee",
        :type "uint256"}
       {:internalType "uint256",
        :name "custodianRedeemWeight",
        :type "uint256"}
       {:internalType "uint256", :name "auditFee", :type "uint256"}
       {:internalType "uint256", :name "mintFee", :type "uint256"}],
      :internalType "struct NFTDataType.NFTFee",
      :name "fee",
      :type "tuple"}
     {:components
      [{:internalType "uint256", :name "value", :type "uint256"}
       {:internalType "address", :name "owner", :type "address"}],
      :internalType "struct NFTDataType.NFTBuyItNow",
      :name "buyItNow",
      :type "tuple"}
     {:components
      [{:internalType "uint256", :name "value", :type "uint256"}
       {:internalType "address", :name "owner", :type "address"}
       {:internalType "address", :name "bidder", :type "address"}
       {:internalType "uint256",
        :name "openOfferTimestamp",
        :type "uint256"}
       {:internalType "uint256",
        :name "closeOfferTimestamp",
        :type "uint256"}],
      :internalType "struct NFTDataType.NFTOffer",
      :name "offer",
      :type "tuple"}
     {:internalType "uint32", :name "totalAuction", :type "uint32"}
     {:internalType "uint32", :name "offerId", :type "uint32"}
     {:internalType "uint32", :name "bidId", :type "uint32"}
     {:internalType "uint256",
      :name "redeemTimestamp",
      :type "uint256"}],
    :internalType "struct NFTDataType.NFTInfo",
    :name "info",
    :type "tuple"}],
  :stateMutability "view",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}
   {:internalType "uint256",
    :name "closeAuctionPeriodSecond",
    :type "uint256"}
   {:internalType "uint256", :name "startingPrice", :type "uint256"}
   {:internalType "uint256", :name "reservePrice", :type "uint256"}
   {:internalType "uint256", :name "maxWeight", :type "uint256"}
   {:internalType "uint256", :name "nextBidWeight", :type "uint256"}],
  :name "openAuction",
  :outputs [],
  :stateMutability "payable",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}
   {:internalType "uint256", :name "value", :type "uint256"}],
  :name "openBuyItNow",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "bidValue", :type "uint256"}
   {:internalType "uint256", :name "tokenId", :type "uint256"}],
  :name "openOffer",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}],
  :name "ownerOf",
  :outputs [{:internalType "address", :name "", :type "address"}],
  :stateMutability "view",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}],
  :name "payFeeAndClaimToken",
  :outputs [],
  :stateMutability "payable",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}],
  :name "processAuction",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}],
  :name "redeem",
  :outputs [],
  :stateMutability "payable",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}],
  :name "redeemCustodianSign",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}],
  :name "revertOffer",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}],
  :name "revertRedeem",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs
  [{:internalType "address", :name "from", :type "address"}
   {:internalType "address", :name "to", :type "address"}
   {:internalType "uint256", :name "tokenId", :type "uint256"}],
  :name "safeTransferFrom",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs
  [{:internalType "address", :name "from", :type "address"}
   {:internalType "address", :name "to", :type "address"}
   {:internalType "uint256", :name "tokenId", :type "uint256"}
   {:internalType "bytes", :name "_data", :type "bytes"}],
  :name "safeTransferFrom",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs
  [{:internalType "address", :name "operator", :type "address"}
   {:internalType "bool", :name "approved", :type "bool"}],
  :name "setApprovalForAll",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs
  [{:internalType "bytes4", :name "interfaceId", :type "bytes4"}],
  :name "supportsInterface",
  :outputs [{:internalType "bool", :name "", :type "bool"}],
  :stateMutability "view",
  :type "function"}
 {:inputs [],
  :name "symbol",
  :outputs [{:internalType "string", :name "", :type "string"}],
  :stateMutability "view",
  :type "function"}
 {:inputs [],
  :name "t",
  :outputs
  [{:internalType "contract ARAToken", :name "", :type "address"}],
  :stateMutability "view",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "tokenId", :type "uint256"}],
  :name "tokenURI",
  :outputs [{:internalType "string", :name "", :type "string"}],
  :stateMutability "view",
  :type "function"}
 {:inputs
  [{:internalType "address", :name "sender", :type "address"}
   {:internalType "address", :name "receiver", :type "address"}
   {:internalType "uint256", :name "tokenId", :type "uint256"}],
  :name "transferFrom",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs
  [{:internalType "address", :name "sender", :type "address"}
   {:internalType "address", :name "receiver", :type "address"}
   {:internalType "uint256", :name "tokenId", :type "uint256"}],
  :name "transferFromCollectionFactory",
  :outputs [], 
  :stateMutability "nonpayable", 
  :type "function"}])

(def collection-factory-abi
  [{:inputs
  [{:internalType "address",
    :name "_governanceContract",
    :type "address"}
   {:internalType "address", :name "_collector", :type "address"}
   {:internalType "string", :name "_name", :type "string"}
   {:internalType "string", :name "_symbol", :type "string"}
   {:internalType "string", :name "_tokenURI", :type "string"}
   {:internalType "uint256", :name "_initialValue", :type "uint256"}
   {:internalType "uint256", :name "_maxWeight", :type "uint256"}
   {:internalType "uint256",
    :name "_collateralWeight",
    :type "uint256"}
   {:internalType "uint256",
    :name "_collectorFeeWeight",
    :type "uint256"}],
  :stateMutability "nonpayable",
  :type "constructor"}
 {:anonymous false,
  :inputs
  [{:indexed true,
    :internalType "address",
    :name "owner",
    :type "address"}
   {:indexed true,
    :internalType "address",
    :name "spender",
    :type "address"}
   {:indexed false,
    :internalType "uint256",
    :name "value",
    :type "uint256"}],
  :name "Approval",
  :type "event"}
 {:anonymous false,
  :inputs
  [{:indexed true,
    :internalType "address",
    :name "from",
    :type "address"}
   {:indexed true,
    :internalType "address",
    :name "to",
    :type "address"}
   {:indexed false,
    :internalType "uint256",
    :name "value",
    :type "uint256"}],
  :name "Transfer",
  :type "event"}
 {:inputs
  [{:internalType "address", :name "owner", :type "address"}
   {:internalType "address", :name "spender", :type "address"}],
  :name "allowance",
  :outputs [{:internalType "uint256", :name "", :type "uint256"}],
  :stateMutability "view",
  :type "function"}
 {:inputs
  [{:internalType "address", :name "spender", :type "address"}
   {:internalType "uint256", :name "amount", :type "uint256"}],
  :name "approve",
  :outputs [{:internalType "bool", :name "", :type "bool"}],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs [],
  :name "auction",
  :outputs
  [{:internalType "uint256",
    :name "openAuctionTimestamp",
    :type "uint256"}
   {:internalType "uint256",
    :name "closeAuctionTimestamp",
    :type "uint256"}
   {:internalType "address", :name "bidder", :type "address"}
   {:internalType "uint256", :name "startingPrice", :type "uint256"}
   {:internalType "uint256", :name "value", :type "uint256"}
   {:internalType "uint256", :name "maxBid", :type "uint256"}
   {:internalType "uint256", :name "maxWeight", :type "uint256"}
   {:internalType "uint256", :name "nextBidWeight", :type "uint256"}
   {:internalType "uint32", :name "totalBid", :type "uint32"}],
  :stateMutability "view",
  :type "function"}
 {:inputs
  [{:internalType "address", :name "account", :type "address"}],
  :name "balanceOf",
  :outputs [{:internalType "uint256", :name "", :type "uint256"}],
  :stateMutability "view",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "bidValue", :type "uint256"}
   {:internalType "uint256", :name "maxBid", :type "uint256"}],
  :name "bidAuction",
  :outputs [],
  :stateMutability "payable",
  :type "function"}
 {:inputs [{:internalType "uint256", :name "amount", :type "uint256"}],
  :name "burn",
  :outputs [],
  :stateMutability "payable",
  :type "function"}
 {:inputs [{:internalType "uint256", :name "amount", :type "uint256"}],
  :name "buy",
  :outputs [],
  :stateMutability "payable",
  :type "function"}
 {:inputs [{:internalType "uint256", :name "amount", :type "uint256"}],
  :name "calculateFundCost",
  :outputs [{:internalType "uint256", :name "", :type "uint256"}],
  :stateMutability "view",
  :type "function"}
 {:inputs [{:internalType "uint256", :name "amount", :type "uint256"}],
  :name "calculateLiquidateCost",
  :outputs [{:internalType "uint256", :name "", :type "uint256"}],
  :stateMutability "view",
  :type "function"}
 {:inputs [{:internalType "uint256", :name "amount", :type "uint256"}],
  :name "calculatePurchaseReturn",
  :outputs [{:internalType "uint256", :name "", :type "uint256"}],
  :stateMutability "view",
  :type "function"}
 {:inputs [{:internalType "uint256", :name "amount", :type "uint256"}],
  :name "calculateSaleReturn",
  :outputs [{:internalType "uint256", :name "", :type "uint256"}],
  :stateMutability "view",
  :type "function"}
 {:inputs [],
  :name "currentCollateral",
  :outputs [{:internalType "uint256", :name "", :type "uint256"}],
  :stateMutability "view",
  :type "function"}
 {:inputs [],
  :name "currentValue",
  :outputs [{:internalType "uint256", :name "", :type "uint256"}],
  :stateMutability "view",
  :type "function"}
 {:inputs [],
  :name "decimals",
  :outputs [{:internalType "uint8", :name "", :type "uint8"}],
  :stateMutability "view",
  :type "function"}
 {:inputs
  [{:internalType "address", :name "spender", :type "address"}
   {:internalType "uint256",
    :name "subtractedValue",
    :type "uint256"}],
  :name "decreaseAllowance",
  :outputs [{:internalType "bool", :name "", :type "bool"}],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs [],
  :name "getInfo",
  :outputs
  [{:components
    [{:internalType "address", :name "collector", :type "address"}
     {:internalType "uint256", :name "maxWeight", :type "uint256"}
     {:internalType "uint256",
      :name "collateralWeight",
      :type "uint256"}
     {:internalType "uint256",
      :name "collectorFeeWeight",
      :type "uint256"}
     {:internalType "uint256",
      :name "dummyCollateralValue",
      :type "uint256"}
     {:internalType "uint32", :name "totalNft", :type "uint32"}
     {:internalType "uint32", :name "totalShareholder", :type "uint32"}
     {:internalType "bool", :name "exists", :type "bool"}
     {:internalType "bool", :name "auction", :type "bool"}
     {:internalType "bool", :name "freeze", :type "bool"}
     {:internalType "string", :name "tokenURI", :type "string"}],
    :internalType "struct CollectionDataType.CollectionInfo",
    :name "i",
    :type "tuple"}],
  :stateMutability "view",
  :type "function"}
 {:inputs [{:internalType "uint32", :name "id", :type "uint32"}],
  :name "getShareholder",
  :outputs
  [{:components
    [{:internalType "address", :name "addr", :type "address"}],
    :internalType "struct CollectionDataType.CollectionShareholder",
    :name "s",
    :type "tuple"}],
  :stateMutability "view",
  :type "function"}
 {:inputs [{:internalType "address", :name "addr", :type "address"}],
  :name "getShareholderIndex",
  :outputs
  [{:components
    [{:internalType "bool", :name "exists", :type "bool"}
     {:internalType "uint32", :name "id", :type "uint32"}],
    :internalType
    "struct CollectionDataType.CollectionShareholderIndex",
    :name "s",
    :type "tuple"}],
  :stateMutability "view",
  :type "function"}
 {:inputs
  [{:internalType "address", :name "spender", :type "address"}
   {:internalType "uint256", :name "addedValue", :type "uint256"}],
  :name "increaseAllowance",
  :outputs [{:internalType "bool", :name "", :type "bool"}],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs [],
  :name "info",
  :outputs
  [{:internalType "address", :name "collector", :type "address"}
   {:internalType "uint256", :name "maxWeight", :type "uint256"}
   {:internalType "uint256", :name "collateralWeight", :type "uint256"}
   {:internalType "uint256",
    :name "collectorFeeWeight",
    :type "uint256"}
   {:internalType "uint256",
    :name "dummyCollateralValue",
    :type "uint256"}
   {:internalType "uint32", :name "totalNft", :type "uint32"}
   {:internalType "uint32", :name "totalShareholder", :type "uint32"}
   {:internalType "bool", :name "exists", :type "bool"}
   {:internalType "bool", :name "auction", :type "bool"}
   {:internalType "bool", :name "freeze", :type "bool"}
   {:internalType "string", :name "tokenURI", :type "string"}],
  :stateMutability "view",
  :type "function"}
 {:inputs
  [{:internalType "address", :name "_collector", :type "address"}
   {:internalType "uint256", :name "_initialAmount", :type "uint256"}
   {:internalType "uint32", :name "_totalNft", :type "uint32"}
   {:internalType "uint256[]", :name "_nfts", :type "uint256[]"}],
  :name "mint",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs [],
  :name "name",
  :outputs [{:internalType "string", :name "", :type "string"}],
  :stateMutability "view",
  :type "function"}
 {:inputs [{:internalType "uint256", :name "maxBid", :type "uint256"}],
  :name "openAuction",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs [],
  :name "processAuction",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs [{:internalType "uint256", :name "amount", :type "uint256"}],
  :name "sell",
  :outputs [],
  :stateMutability "payable",
  :type "function"}
 {:inputs
  [{:internalType "uint256", :name "price", :type "uint256"}
   {:internalType "bool", :name "vote", :type "bool"}],
  :name "setTargetPrice",
  :outputs [],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs [],
  :name "symbol",
  :outputs [{:internalType "string", :name "", :type "string"}],
  :stateMutability "view",
  :type "function"}
 {:inputs [],
  :name "targetPrice",
  :outputs
  [{:internalType "uint256", :name "price", :type "uint256"}
   {:internalType "uint256", :name "totalSum", :type "uint256"}
   {:internalType "uint256", :name "totalVoteToken", :type "uint256"}
   {:internalType "uint32", :name "totalVoter", :type "uint32"}
   {:internalType "uint32", :name "totalVoterIndex", :type "uint32"}],
  :stateMutability "view",
  :type "function"}
 {:inputs [],
  :name "totalSupply",
  :outputs [{:internalType "uint256", :name "", :type "uint256"}],
  :stateMutability "view",
  :type "function"}
 {:inputs
  [{:internalType "address", :name "receiver", :type "address"}
   {:internalType "uint256", :name "amount", :type "uint256"}],
  :name "transfer",
  :outputs [{:internalType "bool", :name "", :type "bool"}],
  :stateMutability "nonpayable",
  :type "function"}
 {:inputs
  [{:internalType "address", :name "sender", :type "address"}
   {:internalType "address", :name "receiver", :type "address"}
   {:internalType "uint256", :name "amount", :type "uint256"}],
  :name "transferFrom",
  :outputs [{:internalType "bool", :name "", :type "bool"}],
  :stateMutability "nonpayable", 
  :type "function"}])

(def contract-abi
  {:member member-abi
   :governance governance-abi
   :ara-token ara-token-abi
   :proposal proposal-abi
   :nft-factory nft-factory-abi
   :collection-factory collection-factory-abi})

(def contract-address
  {:member env/CONTRACT_ADDRESS_MEMBER
   :governance env/CONTRACT_ADDRESS_GOVERNANCE
   :ara-token env/CONTRACT_ADDRESS_ARA_TOKEN
   :proposal env/CONTRACT_ADDRESS_PROPOSAL
   :nft-factory env/CONTRACT_ADDRESS_NFT_FACTORY
   :collection-factory env/CONTRACT_ADDRESS_COLLECTION_FACTORY})


