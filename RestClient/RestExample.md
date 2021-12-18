GET /api.ddaja.com/users/1/licenses/1/moyee-gosa
HTTP/1.1 200 OK
Link: 
 <https://api.ddaja.com/users/1/licenses/1/subjects/1/moyee-gosa?offset=10&limit=10>; rel="next"
, <https://api.ddaja.com/users/1/licenses/1/subjects/1/moyee-gosa?offset=50&limit=10>; rel="last"
, <https://api.ddaja.com/users/1/licenses/1/subjects/1/moyee-gosa?offset=0&limit=10>; rel="first"
, <https://api.ddaja.com/users/1/licenses/1/subjects/1/moyee-gosa?offset=0&limit=0>; rel="prev"

{
    "total_cnt": 120
    , "user": {
        "seq": 1
        , "name": "gil"
        , "id": "gillog"
        , "license": {
            "seq": 1
            , "name": "정보처리기사"
            , "moyee_gosa": [
                {                        
                    "seq": 1
                    , "name": "2021년 1회차 필기"
                    , "created_at": 2021-06-21
                    , "links": [
                        {
                            "rel": "self"
                            , "link": "https://api.ddaja.com/users/1/licenses/1/moyee-gosa/1"
                            , "method": "GET"
                        }
                        , {
                            "rel": "delete"
                            , "link": "https://api.ddaja.com/users/1/licenses/1/moyee-gosa/1"
                            , "method": "DELETE"
                        }
                        , {
                            "rel": "update-all"
                            , "link" "https://api.ddaja.com/users/1/licenses/1/moyee-gosa/1
                            , "method": "PUT" 
                        }
                        , ...
                    ]
                }
                , ...
            ]
        }
    }
}