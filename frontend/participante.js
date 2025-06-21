// Verificar si la convocatoria existe
async function verificarConvocatoriaExistente(idConvocatoria) {
    console.log("Verificando convocatoria con ID: " + idConvocatoria);  // Verificar el valor de ID
    try {
        const response = await fetch(`http://localhost:8080/api/convocatorias/existe/${idConvocatoria}`);
        
        // Verifica que la respuesta sea exitosa
        if (!response.ok) {
            throw new Error('No se pudo verificar la convocatoria');
        }

        const data_1 = await response.json();
        console.log("Respuesta de existencia: ", data_1.exists);  // Verificar la respuesta del servidor
        return data_1.exists;
    } catch (error) {
        console.error('Error al verificar convocatoria:', error);
        return false; // Si hay un error, devolvemos falso
    }
}


// Crear participante
function crearParticipante() {
    const idConvocatoria = document.getElementById('idConvocatoria').value;

    // Verificar si la convocatoria existe
    verificarConvocatoriaExistente(idConvocatoria).then(exists => {
        if (exists) {
            const ruc = document.getElementById('ruc').value;
            const puntajeTecnico = document.getElementById('puntajeTecnico').value;
            const puntajeEconomico = document.getElementById('puntajeEconomico').value;

            // Validar que los valores son correctos antes de enviarlos
            if (!ruc || !puntajeTecnico || !puntajeEconomico || !idConvocatoria) {
                alert("Por favor, completa todos los campos.");
                return;
            }

            const participante = {
                ruc: ruc,
                puntajeTecnico: parseInt(puntajeTecnico),
                puntajeEconomico: parseInt(puntajeEconomico),
                convocatoria: {
                    idConvocatoria: idConvocatoria
                }
            };

            console.log("Datos del participante a crear:", participante); // Verificar los datos antes de enviarlos

            // Enviar la solicitud al servidor
            fetch('http://localhost:8080/api/participantes/crear', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(participante)
            })
            .then(response => {
                if (response.ok) {
                    return response.json(); // Si la respuesta es exitosa, convertir a JSON
                }
                throw new Error('Error al crear el participante');
            })
            .then(data => {
                console.log('Participante creado:', data);
                alert('Participante creado exitosamente');
            })
            .catch(error => {
                console.error('Error al crear participante:', error);
                alert('Error al crear participante');
            });
        } else {
            alert('El ID de la convocatoria no es válido. Por favor, ingresa un ID de convocatoria existente.');
        }
    });
}

// Obtener participantes por ID de Convocatoria
function obtenerParticipantesPorConvocatoria() {
    const idConvocatoria = document.getElementById('idConvocatoriaBusqueda').value;

    // Llamar al backend para obtener los participantes
    fetch(`http://localhost:8080/api/participantes/convocatoria/${idConvocatoria}`)
        .then(response => response.json())
        .then(data => {
            console.log("Respuesta del backend:", data);  // Verifica la respuesta
            if (Array.isArray(data)) {
                const participantesLista = document.getElementById('participantesLista');
                participantesLista.innerHTML = ''; // Limpiar la tabla antes de agregar los nuevos elementos

                // Mostrar la cantidad de participantes
                document.getElementById('cantidadParticipantes').textContent = data.length;

                // Mostrar cada participante en la tabla
                data.forEach(participante => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${participante.ruc}</td>
                        <td>${participante.puntajeTecnico}</td>
                        <td>${participante.puntajeEconomico}</td>
                        <td>${participante.puntajeTotal}</td>
                    `;
                    participantesLista.appendChild(row);
                });
            } else {
                console.error('La respuesta no es un arreglo de participantes:', data);
                alert('Error: La respuesta del servidor no es un arreglo de participantes.');
            }
        })
        .catch(error => {
            console.error('Error al obtener participantes:', error);
            alert('Error al obtener participantes');
        });
}

// Eliminar participante
function eliminarParticipante() {
    const ruc = document.getElementById('rucEliminar').value;

    fetch(`http://localhost:8080/api/participantes/eliminar/${ruc}`, {
        method: 'DELETE',
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al eliminar el participante');
        }
        return response.text();
    })
    .then(data => {
        console.log('Participante eliminado:', data);
        alert('Participante eliminado exitosamente');
    })
    .catch(error => {
        console.error('Error al eliminar participante:', error);
        alert('Error al eliminar participante');
    });
}