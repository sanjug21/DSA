const {noteSchema,contactSchema}=require('./schema')

const validateNote= (req,res,next)=>{
  const { error } = noteSchema.validate(req.body);

  if (error) {
    return res.status(400).json({ error:"unable to validate" });
  }
 
    next();
}

const validateContacts=(req,res,next)=>{
  console.log(req.body)
  const {error}=contactSchema.validate(req.body);
  if(error){
    console.log(error)
    return res.status(400).json({ error:"unable to validate" });
  }
  next();
}
module.exports = {validateNote,validateContacts};