## API for Accounts Keeper (Android Version)

All returns json, and all contains success field.  
success = 0 means error, success = 1 means success.

### User

1. GetUserInfo: GET, http://ip/:user
    * {success: 0, err: msg}
    * {success: 0, username: xxx, nickname: xxx, email: xxx}
1. Auth: POST, http://ip/:user/?username=xxx&password=xxx
    * {success: 0, err: msg}
    * {success: 1, nickname: xxx, email: xxx}
2. Register: POST, http://ip/:user
    * {success: 0, err: msg}
    * {success: 1}
3. ChangeNickname: POST, http://ip/:user/nickname?nickname=xxx
    * {success: 0, err: msg}
    * {success: 1}
3. ChangePassword: POST, http://ip/:user?password=xxx&newpwd=xxx
    * {success 0, err: msg}
    * {success: 1}