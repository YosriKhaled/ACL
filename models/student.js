
var mongoose = require("mongoose");
var Schema = mongoose.Schema;



var studentSchema = new Schema({

  name: {
    type: String,
    required: true
  },
  studentId: {
    type: String,
    required: true
  },
  password: {
    type: String,
    required: true,
    
  },
  
  
});

var student = mongoose.model("student", studentSchema);

module.exports = student;
