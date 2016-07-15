var mongoose = require('mongoose');
var Schema = mongoose.Schema;
var bcrypt = require('bcrypt');

var SALT_WORK_FACTOR = 10;

var UserSchema = new Schema({
  username: { 
    type: String,
    required: true
  },
  password: {
    type: String,
    required: true
  },
  nickname: {
    type: String,
    default: ''
  },
  email: {
    type: String,
    default: ''
  }
});

// Before saving to database.
UserSchema.pre('save', function(next) {
  var user = this;

  // Only hash the password that has been modified (or new)
  if (!user.isModified('password'))
    return next();

  // Generate an salt for robust.
  bcrypt.genSalt(SALT_WORK_FACTOR, function(err, salt) {
    if (err)
      return next(err);

    bcrypt.hash(user.password, salt, function(err, hash) {
      if (err)
        return next(err);

      // override the cleartext password with hashed (add salt) one
      user.password = hash;
      next();
    });
  });
});

UserSchema.methods.auth = function(pwd, cb) {
  bcrypt.compare(pwd, this.password, function(err, isMatch) {
    if (err)
      return cb(err);
    cb(null, isMatch);
  });
};

module.exports = mongoose.model('User', UserSchema, 'User');
