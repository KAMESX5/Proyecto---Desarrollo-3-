# Documento de Arquitectura del Sistema de Gestión de Órdenes y Entregas

## 1. Introducción

Este documento describe la arquitectura inicial del sistema de gestión de órdenes y entregas, incluyendo requisitos funcionales, requisitos de calidad y restricciones clave que deben ser consideradas en el diseño del software.

**Equipo:** *[Equipo 11]*  
**Integrantes:** *[Juan Sebastian Barona - 2266123*
                *Jesús Estenllos loaiza serrano - 2313021* 
                *Kevin Alejandro Marulanda Escobar - 2380697]*  
**Fecha:** *[25/02/2025]*  

---

## 2. Requisitos Funcionales

Los requisitos funcionales se presentan en forma de **historias de usuario**, especificando los **criterios de aceptación**.

### **Historias de Usuario**

| **ID**   | **Historia de Usuario** | **Criterios de Aceptación** |
|----------|-------------------------|-----------------------------|
| **US-01** | Como cliente registrado, quiero iniciar sesión en la plataforma para acceder a mi historial de pedidos y realizar nuevas compras. | - El usuario debe ingresar un correo y contraseña válidos.  
- Si las credenciales son correctas, el usuario es redirigido a su perfil.  
- Si las credenciales son incorrectas, se muestra un mensaje de error sin revelar detalles específicos.  
- El usuario puede solicitar un restablecimiento de contraseña. |
| **US-02** | Como administrador, quiero generar reportes de ventas para analizar el rendimiento del negocio. | - El usuario debe poder seleccionar un rango de fechas.  
- El sistema debe generar un informe detallado con ventas, productos más vendidos y ganancias.  
- El informe debe poder exportarse en formato PDF o Excel. |
| **US-03** | Como repartidor, quiero ver una lista de entregas asignadas para organizar mi ruta de trabajo. | - El sistema debe mostrar una lista de entregas pendientes con detalles del cliente y dirección.  
- Debe permitir marcar una entrega como completada.  
- Debe actualizar en tiempo real el estado de la entrega en el sistema. |
| **US-04** | Como cliente, quiero recibir notificaciones en tiempo real sobre el estado de mi pedido. | - El sistema debe enviar notificaciones en cada cambio de estado del pedido (confirmado, en preparación, en camino, entregado).  
- Las notificaciones deben enviarse por correo y notificaciones push en la aplicación móvil. |

> **Instrucciones:**
> - Completar al menos **tres historias de usuario**.
> - Asegurar que cada historia tenga criterios de aceptación claros y verificables.

---

## 3. Requisitos de Calidad

Los requisitos de calidad se presentan en forma de **historias de calidad**, siguiendo la estructura de Len Bass.

### **Historias de Calidad**

| **ID**   | **Fuente** | **Estímulo** | **Artefactos** | **Entorno** | **Respuesta** | **Medida de Respuesta** |
|----------|-----------|-------------|--------------|------------|------------|----------------|
| **RQ-01** | Desarrollador | Se solicita modificar la lógica de asignación de pedidos para incluir nuevos criterios de prioridad. | Código y configuración de reglas de negocio. | Tiempo de ejecución | Se debe modificar, probar y desplegar la nueva lógica de asignación. | El esfuerzo requerido no debe superar 2 horas de trabajo y no deben generarse más de 2 defectos nuevos. |
| **RQ-02** | Administrador | Se solicita implementar la prioridad de seguimiento de las diferentes notificaciones en las compras. | Código y configuración de las notificaciones del sistema de Entregas. | Tiempo de Ejecución | Se debe modificar el sistema de Notificaciones, hacer las pruebas y Asignar las prioridades dentro del sistema de Notificaciones. | El esfuerzo requerido no debe superar 5 horas de trabajo y no deben generarse más de 3 defectos nuevos. |
| **RQ-03** | Repartidor | Se solicita implementar el Login para el acceso correspondiente de Repartidores. | Código y configuración del sistema de Login en el entorno de Repartidores. | Tiempo de Espera | Se debe modificar, probar y desplegar el login. | El esfuerzo requerido no debe superar 2 horas de trabajo y no deben generarse más de 2 defectos nuevos. |
| **RQ-04** | Cliente | Se solicita añadir una funcionalidad para seguimiento en tiempo real del pedido. | Código de gestión de seguimiento en tiempo real del pedido y API de notificaciones. | Tiempo de ejecución | Se debe modificar, probar y desplegar la funcionalidad del seguimiento en tiempo real, y la implementación de pruebas para notificaciones. | El esfuerzo requerido no debe superar las 10 horas de trabajo y no deben generarse más de 3 defectos nuevos. |

> **Instrucciones:**
> - Completar al menos **tres historias de calidad**, alineadas con atributos clave como **rendimiento, escalabilidad y seguridad**.
> - Definir cómo se medirá la respuesta esperada ante la situación planteada.

---

## 4. Restricciones del Sistema

Las restricciones establecen **limitaciones** en la arquitectura del sistema, ya sean tecnológicas, de negocio, regulatorias o de infraestructura.

### **Lista de Restricciones**

| **Tipo de Restricción** | **Descripción** |
|----------------|----------------|
| Tecnológica | El sistema debe desarrollarse utilizando **Javascript y PostgreSQL**, debido a la infraestructura actual de la empresa y su compatibilidad con otros sistemas internos. |
| De negocio | El sistema debe cumplir con la normativa interna de gestión de pedidos y tiempos de entrega establecidos por la empresa. |
| Regulatoria | El sistema debe cumplir con la Ley de Protección de Datos vigente en el país de operación. |
| Infraestructura | El sistema debe ser capaz de ejecutarse en la infraestructura de servidores actuales sin necesidad de inversiones adicionales. |

> **Tipos de restricciones:**
> - **Tecnológicas:** Lenguajes, frameworks o herramientas que deben utilizarse.
> - **De negocio:** Normativas o estándares de la empresa.
> - **Regulatorias:** Cumplimiento de normativas legales o de seguridad.
> - **De infraestructura:** Limitaciones en hardware, red o almacenamiento.

---

## 5. Formato de Entrega

- **Formato:** Markdown (`.md`) en el repositorio de Codelabs. El documento debe llamarse `Arquitectura.md`.
- **Extensión máxima:** 3 páginas.

---

## **Tiempo estimado para completar el ejercicio: 50 minutos**

- **15 min** → Definir **3 historias de usuario con criterios de aceptación**.  
- **15 min** → Definir **3 historias de calidad alineadas con atributos clave**.  
- **10 min** → Identificar **2 restricciones relevantes**.  
- **10 min** → Revisión y ajustes finales del documento.
