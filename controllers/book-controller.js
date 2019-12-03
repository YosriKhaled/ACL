
const Joi = require('joi')
const book = require('../models/book')



exports.listAllbooks = async (req, res) => {
  try {
    const books = await book.find()
    res.json(books)
  } catch (error) {
    console.log(error)
  }
}

exports.getbook = async (req, res) => {
  try {
    const book = await book.findById(req.params.id)

    // Send 404 error if not found
    if (!book) return res.status(404).send(`book not found.`)

    res.json(electronicJournal)
  } catch (error) {
    console.log(error)
  }
}

exports.addbook = async (req, res) => {
  try {
    // Validate using destructring = result.error to simplify the code
    const { error } = validatebook(req.body)

    // If invalid, return 400 - Bad request
    if (error) return res.status(400).send(error.details[0].message)
    // Create the electronic Journal, add to the array and display it.
    const newbook = await book.create(req.body)
    res.json(newbook)
  } catch (error) {
    console.log(error)
  }
}

exports.updatebook = async (req, res) => {
  try {
    // Validate using destructring = result.error, to simplify the code
    const { error } = validatebook(req.body)

    // If invalid, return 400 - Bad request
    if (error) return res.status(400).send(error.details[0].message)

    // Lookup the electronic Journal and update it
    const updatedbook = await book.findByIdAndUpdate(req.params.id, req.body, { new: true })

    // If not existing, return 404
    if (!updatedbook) return res.status(404).send(`book not found`)

    // Return the updated electronic Journal
    res.json(updatedbook)
  } catch (error) {
    // We will be handling the error later
    console.log(error)
  }
}

exports.removebook = async (req, res) => {
  try {
    // Lookup the electronic Journal and Delete it
    const removedbook = await book.findByIdAndRemove(req.params.id)

    // Not existing, return 404
    if (!removedbook) return res.status(404).send(`book not found`)

    // Return the same reviewer by convention
    res.json(removedbook)
  } catch (error) {
    console.log(error)
  }
}

function validatebook (book) {
  const schema = {

    name: Joi.string()
      .max(30)
      .required(),
   availability: Joi.boolean()

    
      .required(),
  }

  return Joi.validate(book, schema)
}
