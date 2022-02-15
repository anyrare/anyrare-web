(ns anyrare-web.env
  (:require-macros [adzerk.env :as env]))

(env/def GRAPHQL_SERVER_HTTP "http://localhost:8080")
(env/def GRAPHQL_SERVER_WS "ws://localhost:8080/graphql-ws")
(env/def CONTRACT_ADDRESS_MEMBER "0xe4E624C0137dBf1fB90Ed9BE08069142727ba965")
(env/def CONTRACT_ADDRESS_GOVERNANCE "0xb462230AbFAB8610AEe35516B4186d3BCBAE5eC5")
(env/def CONTRACT_ADDRESS_ARA_TOKEN "0x8f4dEdDfB5a29b4dafA133311a78677f1b90e102")
(env/def CONTRACT_ADDRESS_PROPOSAL "0xaE2D1a1d4CC05B0a3b42902Eb01FB92F6B890614")
(env/def CONTRACT_ADDRESS_NFT_FACTORY "0xd2a9d52071B8dc324C0406355B3A2D507db3C6FC")
(env/def CONTRACT_ADDRESS_COLLECTION_FACTORY "0x7b4A388AA5818b414ACbe8F2A4C707d7D3129bf8")
(env/def CHAIN_URL "https://testnet.anyrare.network")
(env/def CHAIN_ID 1687)
(env/def CHAIN_NAME "anyrare")
(env/def ENABLED_ERROR_LOG true)
(env/def AUCTION_MAX_WEIGHT 1000000)
(env/def AUCTION_NEXT_BID_WEIGHT 50000)
(env/def PINATA_API_KEY "98859962ad7397103324")
(env/def PINATA_API_SECRET "2369ea05f93770cf30851270ae2da2a0b41ed0307cf3ea9e9c98e710d859b844")
(env/def PINATA_SERVER_URL "https://api.pinata.cloud")
(env/def IPFS_SERVER_URL "https://ipfs.anyrare.network")
