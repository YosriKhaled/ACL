
var mongoose = require("mongoose");
var Schema = mongoose.Schema;



var bookSchema = new Schema({

  name: {
    type: String,
    required: true
  },
  availability: {
    type: Boolean,
    required: true
  }
  
  
});

var book = mongoose.model("book", bookSchema);

module.exports = book;
