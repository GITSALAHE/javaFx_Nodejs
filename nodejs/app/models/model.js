const sql = require("./db.js");

const Formation = function(post) {
  
  };

  Formation.findById = (formId, result) => {
    sql.query(`SELECT session.code,session.libelle,session.formation, formation.libelle as 'topic', formation.description FROM session INNER JOIN formation ON formation.code = session.formation WHERE formation = '${formId}'`, (err, res) => {
      if (err) {
        console.log("error: ", err);
        result(err, null);
        return;
      }
  
      if (res.length) {
        console.log("found post: ", res);
        result(null, res);
        return;
      }
  
      // not found post with the id
      result({ kind: "not_found" }, null);
    });
  };
  module.exports = Formation;
