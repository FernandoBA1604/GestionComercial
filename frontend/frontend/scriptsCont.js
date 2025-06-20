document.addEventListener('DOMContentLoaded', function () {
  const opcionesCliente = ['Participante A', 'Participante B', 'Participante C'];
  const filaClientes = new Map(); 

  fetch('http://localhost:8080/api/subida/uploadTest')
    .then(response => {
      if (!response.ok) throw new Error('Error al obtener convocatorias');
      return response.json();
    })
    .then(data => {
      const tbody = document.querySelector('#tablaConvocatorias tbody');

      if (!Array.isArray(data) || data.length === 0) {
        tbody.innerHTML = '<tr><td colspan="11">No hay datos disponibles</td></tr>';
        return;
      }

      const datalist = document.createElement('datalist');
      datalist.id = 'clientes-list';
      opcionesCliente.forEach(opcion => {
        const option = document.createElement('option');
        option.value = opcion;
        datalist.appendChild(option);
      });
      document.body.appendChild(datalist);

      data.forEach(c => {
        const fila = document.createElement('tr');

        const clienteInput = document.createElement('input');
        clienteInput.type = 'text';
        clienteInput.className = 'cliente-input';
        clienteInput.setAttribute('list', 'clientes-list');
        clienteInput.value = c.cliente || '';
        
        filaClientes.set(c.idConvocatoria, {
          input: clienteInput
        });

        fila.innerHTML = `
          <td>${c.idConvocatoria}</td>
          <td>${c.nombreEntidad || ''}</td>
          <td>${formatearFecha(c.fechaPublicacion)}</td>
          <td>${c.tipoSeleccion || ''}</td>
          <td>${c.objetoContratacion || ''}</td>
          <td>${c.descripcion || ''}</td>
          <td>${c.alcance || ''}</td>
          <td>${c.cantidad ?? ''}</td>
          <td>${c.plazoDias ?? ''}</td>
          <td>${formatearFecha(c.fechaConvocatoria)}</td>
        `;

        const tdCliente = document.createElement('td');
        tdCliente.appendChild(clienteInput);
        fila.appendChild(tdCliente);
        tbody.appendChild(fila);
      });
    })
    .catch(error => {
      console.error('Error al cargar convocatorias:', error.message);
    });

  function formatearFecha(fechaISO) {
    if (!fechaISO) return '';
    const date = new Date(fechaISO);
    return date.toLocaleDateString();
  }

  document.getElementById('btnRegresar').addEventListener('click', function() {
    window.location.href = 'index.html';
  });

  document.getElementById('btnGuardarTodos').addEventListener('click', () => {
    let totalExitosos = 0;
    const promesas = [];

    filaClientes.forEach((info, idConvocatoria) => {
      const cliente = info.input.value;

      const promesa = fetch(`http://localhost:8080/api/subida/actualizarCliente/${idConvocatoria}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ cliente })
      })
        .then(response => response.text().then(texto => {
          if (!response.ok) throw new Error(texto);
        }))
        .catch(error => {
          alert(`Error en convocatoria ${idConvocatoria}: ${error.message}`);
        });

      promesas.push(promesa);
    });

    Promise.all(promesas).then(() => {
      alert(`Guardado completo.`);
      location.reload();
    });
  });
});