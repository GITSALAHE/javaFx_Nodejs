const Formation = require("../models/model.js");

exports.findOne = (req, res) => {
  
  Formation.findById(req.params.formId, (err, data) => {
    var fomationObj = {};
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Post with id ${req.params.formId}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Post with id " + req.params.formId
        });
      }
    } else{
      fomationObj = {
          fs : data
      }
     res.render('index', fomationObj)
    }
    
  });
};


