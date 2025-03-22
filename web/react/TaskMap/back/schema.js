const Joi = require('joi');

const noteSchema = Joi.object({
  title: Joi.string().trim().max(100).allow(null, '').default('Untitled').messages({
    'string.max': 'Title cannot exceed 100 characters',
  }),
  description: Joi.string().trim().min(1).required().messages({
    'string.empty': 'Description is required',
    'string.min': 'Description must be at least 1 character',
    'any.required': 'Description is required',
  }),
});

const contactSchema = Joi.object({
  name: Joi.string().required().messages({
    'string.empty': `"name" cannot be an empty field`,
    'any.required': `"name" is a required field`,
  }),
  email: Joi.string().email().allow(null, '').messages({
    'string.email': `"email" must be a valid email`,
  }),
  phone: Joi.string().required().messages({
    'string.empty': `"phone" cannot be an empty field`,
    'any.required': `"phone" is a required field`,
  }),
  secondaryPhone: Joi.string().allow(null, '').messages({}),
  secondaryEmail: Joi.string().email().allow(null, '').messages({
    'string.email': `"secondaryEmail" must be a valid email`,
  }),
  dob: Joi.date().allow(null, '').messages({
    'date.base': `"dob" must be a valid date`,
  }),
});

module.exports = {
  noteSchema,
  contactSchema,
};