var User = require('../models/user');

exports.get = function(app) {
  function get(req, res) {
    User.findOne({ username: req.params.username }, function(err, user) {
      if (err)
        res.json({ success: 0, err: '服务器出了点小故障，请稍后再试！' });
      else if (!user)
        res.json({ success: 0, err: '用户不存在！' });
      else
        res.json({ success: 1, username: user.username, nickname: user.nickname, email: user.email });
      }
    );
  }

  return get;
}

exports.auth = function(app) {
  function auth(req, res) {
    User.findOne({ username: req.params.username }, function(err, user) {
      if (err)
        res.json({ success: 0, err: '服务器出了点小故障，请稍后再试！' });
      else if (!user)
        res.json({ success: 0, err: '用户不存在！' });
      else {
        user.auth(req.body.password, function(err, isMatch) {
          if (err)
            res.json({ success: 0, err: '服务器出了点小故障，请稍后再试！' });
          else if (isMatch)
            res.json({ success: 1, nickname: user.nickname, email: user.email });
          else
            res.json({ success: 0, err: '用户名或密码错误！' });
        });
      }
    });
  }

  return auth;
}

exports.post = function(app) {
  function post(req, res) {
    User.findOne({ username: req.body.username }, function(err, user) {
      if (err) {
        res.json({ success: 0, err: '服务器出了点小故障，请稍后再试！' });
        return;
      } else if (user) {
        res.json({ success: 0, err: '用户名已存在，请换一个用户名！' });
        return;
      }
    });

    var newUser = {
      username: req.body.username,
      password: req.body.password,
      nickname: req.body.nickname.toString("utf8"),
      email: req.body.email
    };

    var user = new User(newUser);
    user.save({ username: req.body.username }, function(err) {
        if (err)  {
          res.err({ success: 0, err: '服务器出了点小故障，请稍后再试！' });
        } else
          res.json({ success: 1 });
      });
  }

  return post;
}

exports.changeNickname = function(app) {
  function change(req, res) {
    User.findOne({ username: req.params.username }, function(err, user) {
      if (err)
        res.json({ success: 0, err: '服务器出了点小故障，请稍后再试！' });
      else if (!user)
        res.json({ success: 0, err: '用户不存在！' });
      else {
        user.update({ $set: {nickname: req.body.nickname}}, function(err, doc) {
          if (err)
            res.json({ success: 0, err: '服务器出了点小故障，请稍后再试！' });
          else
            res.json({ success: 1});
        });
      }
    });
  }

  return change;
}

exports.changePassword = function(app) {
  function change(req, res) {
    User.findOne({ username: req.params.username }, function(err, user) {
      if (err)
        res.json({ success: 0, err: '服务器出了点小故障，请稍后再试！' });
      else if (!user)
        res.json({ success: 0, err: '用户不存在！' });
      else {
        user.auth(req.body.password, function(err, isMatch) {
          if (err)
            res.json({ success: 0, err: '服务器出了点小故障，请稍后再试！' });
          else if (isMatch) {
            user.password = req.body.password; // Because bcrypt doesn't work for update so use save instead
            user.save(function() {
              if (err)
                res.json({ success: 0, err: '服务器出了点小故障，请稍后再试！' });
              else
                res.json({ success: 1 });
            })
          } else
            res.json({ success: 0, err: '原始密码错误！' });
        });
      }
    });

    User.findOne({ username: req.params.username }, function(err, user) {
      if (err)
        res.json({ success: 0, err: '服务器出了点小故障，请稍后再试！' });

    })
  }

  return change;
}