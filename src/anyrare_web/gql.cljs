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
    }}"}})

