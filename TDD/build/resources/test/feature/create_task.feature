Feature: Crear nueva tarea

  Scenario: Crear nueva tarea
    Given el sistema está operativo
    When envío un POST a /tasks con título y descripción
    Then la tarea es creada y la respuesta contiene JSON con id, título y descripción