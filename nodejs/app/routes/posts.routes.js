const express = require("express");

module.exports = app => {
  const posts = require("../controllers/controller.js");

  app.get("/formation/(:formId)", posts.findOne);

};
