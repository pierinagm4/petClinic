
Hecho por Pierina Galvez

Desarrollo de una API basada en REST

Para ingresar a swagger ir a link http://localhost:8080/swagger-ui/index.html

Funcionalidades requeridas

UserController.saveCustomer devuelve un cliente guardado que coincide con la solicitud
UserController.saveEmployee devuelve un empleado guardado que coincide con la solicitud
PetController.getPetByOwner devuelve una mascota guardada con el mismo id y nombre que la mascota guardada en UserController.savePet para un propietario determinado
PetController.getPetsByOwner devuelve todas las mascotas guardadas para ese propietario.
UserController.getOwnerByPet devuelve el propietario guardado utilizado para crear la mascota.
UserController.getEmployee devuelve un empleado con la misma disponibilidad establecida para ese empleado por UserController.setAvailability
UserController.findEmployeesForService devuelve todos los empleados guardados que tienen la disponibilidad y las habilidades solicitadas.
ScheduleController.createSchedule devuelve un calendario guardado que coincide con las actividades solicitadas, las mascotas, los empleados y la fecha
ScheduleController.getScheduleForEmployee devuelve todos los calendarios guardados que contienen a ese empleado.
ScheduleController.getScheduleForPet devuelve todos los horarios guardados para esa mascota.
ScheduleController.getScheduleForCustomer devuelve todos los horarios guardados para cualquier mascota que pertenezca a ese propietario.

