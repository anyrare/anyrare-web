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
    }}"}})

