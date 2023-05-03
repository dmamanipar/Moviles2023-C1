package pe.edu.upeu.modelo

data class User(
    var name: String,
    var email:String,
    var password:String
)


data class UserResponse(
    var status:Boolean,
    var message:String,
    var access_token:String,
    var token_type:String,
    var expires_at:String
)