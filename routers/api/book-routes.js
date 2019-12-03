const express = require('express')
const router = express.Router()
const bookController = require('../../controllers/book-controller')

router.use(express.json())

router.get('/', bookController.listAllbooks)
router.get('/:id', bookController.getbook)

router.post('/', bookController.addbook)
router.put('/:id', bookController.updatebook)
router.delete('/:id', bookController.removebook)

module.exports = router
