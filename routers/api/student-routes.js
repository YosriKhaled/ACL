const express = require('express')
const router = express.Router()
const studentController = require('../../controllers/student-controller')

router.use(express.json())

router.get('/', studentController.listAllStudents)
router.get('/:id', studentController.getStudent)

router.post('/', studentController.addStudent)
router.put('/:id', studentController.updateStudent)
router.delete('/:id', studentController.removeStudent)

module.exports = router
