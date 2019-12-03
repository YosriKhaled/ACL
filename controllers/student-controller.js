
const Joi = require('joi')
const student = require('../models/student')



exports.listAllStudents = async (req, res) => {
  try {
    const students = await student.find()
    res.json(students)
  } catch (error) {
    console.log(error)
  }
}

exports.getStudent = async (req, res) => {
  try {
    const student = await student.findById(req.params.id)

    // Send 404 error if not found
    if (!student) return res.status(404).send(`student not found.`)

    res.json(electronicJournal)
  } catch (error) {
    console.log(error)
  }
}

exports.addStudent = async (req, res) => {
  try {
    // Validate using destructring = result.error to simplify the code
    const { error } = validateStudent(req.body)

    // If invalid, return 400 - Bad request
    if (error) return res.status(400).send(error.details[0].message)
    // Create the electronic Journal, add to the array and display it.
    const newStudent = await student.create(req.body)
    res.json(newStudent)
  } catch (error) {
    console.log(error)
  }
}

exports.updateStudent = async (req, res) => {
  try {
    // Validate using destructring = result.error, to simplify the code
    const { error } = validateStudent(req.body)

    // If invalid, return 400 - Bad request
    if (error) return res.status(400).send(error.details[0].message)

    // Lookup the electronic Journal and update it
    const updatedStudent = await student.findByIdAndUpdate(req.params.id, req.body, { new: true })

    // If not existing, return 404
    if (!updatedStudent) return res.status(404).send(`student not found`)

    // Return the updated electronic Journal
    res.json(updatedStudent)
  } catch (error) {
    // We will be handling the error later
    console.log(error)
  }
}

exports.removeStudent = async (req, res) => {
  try {
    // Lookup the electronic Journal and Delete it
    const removedStudent = await student.findByIdAndRemove(req.params.id)

    // Not existing, return 404
    if (!removedStudent) return res.status(404).send(`student not found`)

    // Return the same reviewer by convention
    res.json(removedStudent)
  } catch (error) {
    console.log(error)
  }
}

function validateStudent (student) {
  const schema = {

    name: Joi.string()
      .max(30)
      .required(),
    studentId: Joi.string().min(3).max(200).required(),
    password: Joi.string()
      .min(8)
      .max(30)
      .required(),
  }

  return Joi.validate(student, schema)
}
