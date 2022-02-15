(ns anyrare-web.gql)

(defonce gql
  {:member-by-code
   {:name "member_by_code"
    :type :query
    :query "query member_by_code($code: String!) {
    member_by_code(code: $code) {
      address
      referral
      code
      username
      thumbnail
    }}"}

   :create-member
   {:name "create_member"
    :type :mutation
    :query "mutation create_member(
    $address: String!
    $referral: String!
    $code: String!
    $username: String!
    $thumbnail: String!
    ) {
    create_member(
      address: $address
      referral: $referral
      code: $code
      username: $username
      thumbnail: $thumbnail
    ) {
      address
      referral
      code
      username
      thumbnail
    }}"}

   :get-nft
   {:name "getNFT"
    :type :query
    :query "query GetNFT($tokenId: Int!) {
    getNFT(tokenId: $tokenId) {
      id
      tokenId
      tokenURI
      tokenURIData {
        address
        founderAddress
        auditorAddress
        custodianAddress
        assets {
          type
          url
        }
        title {
          th
        }
        description {
          th
        }
        auditor {
          report
          assets {
            type
            url
          }
          timestamp
        }
      }
      founderAddress
      founder {
        address
        username
        thumbnail
      }
      ownerAddress
      owner {
        address
        username
        thumbnail
      }
      auditorAddress
      auditor {
        address
        username
        thumbnail
      }
      custodianAddress
      custodian {
        address
        username
        thumbnail
      }
      feeMaxWeight
      feeFounderWeight
      feeFounderGeneralFee
      feeFounderRedeemWeight
      feeCustodianWeight
      feeAuditFee
      feeMintFee
      statusAuction
      statusLockInCollection
      statusBuyItNow
      statusOffer
      statusRedeem
      statusFreeze
      auditorReportURI
      auditorDescription
      custodianContractURI
      buyItNowPrice
      totalAuction
      totalOffer
    }}"}

   :submit-job
   {:name "submitJob"
    :type :mutation
    :query "mutation SubmitJob(
    $function: String!
    $params: String!) {
    submitJob(
      function: $function
      params: $params
    ) {
      jobId
      pendingProcess
      function
      params
    }}"}})
