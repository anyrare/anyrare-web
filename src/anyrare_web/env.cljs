(ns anyrare-web.env
  (:require-macros [adzerk.env :as env]))

(env/def GRAPHQL_SERVER_HTTP "http://localhost:8080")
(env/def GRAPHQL_SERVER_WS "ws://localhost:8080/graphql-ws")
(env/def CONTRACT_ADDRESS_MEMBER "0xC26fa26b0666FEf44E3Fd73773956D36A2F60Ad9")
(env/def CONTRACT_ADDRESS_GOVERNANCE "0x76A50B30B6FA04bf1B4D870996Cedede6072d93c")
(env/def CONTRACT_ADDRESS_ARA_TOKEN "0x90606171157961AE1C15ACE1792B15de2E66145F")
(env/def CONTRACT_ADDRESS_PROPOSAL "0xe87C32e8907D179d787E7144969D8DD1c311C317")
(env/def CONTRACT_ADDRESS_NFT_FACTORY "0x895A3fb46883D2CA9396B4AFA99CF2dfff8dA629")
(env/def CONTRACT_ADDRESS_COLLECTION_FACTORY "0x401b144263c685Df56D17e2766A0BC4C8Ca5c230")
(env/def CHAIN_URL "https://testnet.anyrare.network")
(env/def CHAIN_ID 1687)
(env/def CHAIN_NAME "anyrare")
(env/def ENABLED_ERROR_LOG true)
(env/def AUCTION_MAX_WEIGHT 1000000)
(env/def AUCTION_NEXT_BID_WEIGHT 50000)
