document.addEventListener('DOMContentLoaded', function () {
  const fileInput = document.getElementById('archivo');
  const fileInfo = document.getElementById('fileInfo');
  const fileNameSpan = document.getElementById('fileName');
  const fileSizeSpan = document.getElementById('fileSize');
  const previewImage = document.getElementById('previewImage');
  const nonImagePreview = document.getElementById('nonImagePreview');
  const filePreview = document.getElementById('filePreview');
  const successMessage = document.getElementById('successMessage');
  const failedMessage = document.getElementById('failedMessage');

  let selectedFile = null;

  fileInput.addEventListener('change', () => {
    selectedFile = fileInput.files[0];
    if (!selectedFile) return;

    fileNameSpan.textContent = selectedFile.name;
    fileSizeSpan.textContent = (selectedFile.size / 1024).toFixed(1);
    fileInfo.classList.remove('hidden');

    if (selectedFile.type.startsWith('image/')) {
      const reader = new FileReader();
      reader.onload = function (e) {
        previewImage.src = e.target.result;
        nonImagePreview.classList.add('hidden');
        previewImage.classList.remove('hidden');
        filePreview.classList.remove('hidden');
      };
      reader.readAsDataURL(selectedFile);
    } else {
      previewImage.classList.add('hidden');
      nonImagePreview.classList.remove('hidden');
      nonImagePreview.textContent = `Tipo de archivo: ${selectedFile.type || 'Desconocido'}`;
      filePreview.classList.remove('hidden');
    }
  });

  document.getElementById('uploadForm').addEventListener('submit', function (event) {
    event.preventDefault();
    if (!selectedFile) {
      alert('Por favor, selecciona un archivo antes de enviar.');
      return;
    }
    const formData = new FormData();
    formData.append('file', selectedFile);
    formData.append('sheetIndex', 0);


    fetch('http://localhost:8080/api/subida/uploadTest', {
    method: 'POST',
    body: formData
    })
    .then(response => {
      if (response.ok) {
        return response.text();
      } else {
        return response.text().then(errorText => {
          throw new Error(`Error del servidor ${errorText || 'respuesta no exitosa'}`);
        });
      }
    })
    .then(data => {
    console.log('Archivo subido exitosamente:', data);
    successMessage.classList.remove('hidden');
    setTimeout(() => {
      successMessage.classList.add('hidden');
      window.location.href = 'continuar.html';
    }, 1500);
    })
    .catch(error => {
      console.error('Error durante la subida del archivo:', error.message);
      failedMessage.classList.remove('hidden');
      setTimeout(() => {
        failedMessage.classList.add('hidden');
      }, 3000);
    });

    setTimeout(() => {
      fileInput.value = ''; 
      selectedFile = null;
      fileInfo.classList.add('hidden');
      filePreview.classList.add('hidden');
      previewImage.src = '';
      nonImagePreview.textContent = '';
    }, 1500);
  });

  document.getElementById('regresarButton')?.addEventListener('click', () => {
    window.location.href = 'index.html';
  });
});