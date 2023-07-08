var volumeControl;
var recordVideo;
function getHTMLMediaElement(mediaElement, config) {
	config = config || {};

	var buttons = config.buttons || [ 'record-video' ];
	buttons.has = function(element) {
		return buttons.indexOf(element) !== -1;
	};

	config.toggle = config.toggle || [];
	config.toggle.has = function(element) {
		return config.toggle.indexOf(element) !== -1;
	};

	var mediaElementContainer = document.getElementById('videos-container');
//	mediaElementContainer.className = 'media-container';

	var mediaControls = document.getElementById('media-controls');
//	mediaControls.className = 'media-controls';
//	mediaElementContainer.appendChild(mediaControls);

	volumeControl = document.getElementById('volume-control');
//	volumeControl.className = 'volume-control';
	
	if (buttons.has('record-video')) {
//		recordVideo = document.createElement('div');
		recordVideo = document.getElementById('recoed-video');
		recordVideo.className = 'control ' + (config.toggle.has('record-video') ? 'stop-recording-video selected' : 'record-video');
		volumeControl.appendChild(recordVideo);

			if (recordVideo.className.indexOf('stop-recording-video') != -1) {
				recordVideo.className = recordVideo.className.replace('stop-recording-video selected', 'record-video');
			} else {
				recordVideo.className = recordVideo.className.replace('record-video', 'stop-recording-video selected');
			}
	}
	

//	if (buttons.has('record-video')) {
//		recordVideo = document.createElement('div');
//		recordVideo.className = 'control ' + (config.toggle.has('record-video') ? 'stop-recording-video selected' : 'record-video');
//		volumeControl.appendChild(recordVideo);
//
//		recordVideo.onclick = function() {
//			if (recordVideo.className.indexOf('stop-recording-video') != -1) {
//				recordVideo.className = recordVideo.className.replace('stop-recording-video selected', 'record-video');
//				if (config.onRecordingStopped)
//					config.onRecordingStopped('video');
//			} else {
//				recordVideo.className = recordVideo.className.replace('record-video', 'stop-recording-video selected');
//				if (config.onRecordingStarted)
//					config.onRecordingStarted('video');
//			}
//		};
//	}

//	if (buttons.has('record-video')) {
//		mediaElementContainer.appendChild(volumeControl);
//	}

//	var mediaBox = document.createElement('div');
//	var mediaBox = document.getElementById('media-box');
//	mediaBox.className = 'media-box';
//	mediaElementContainer.appendChild(mediaBox);

//	mediaBox.appendChild(mediaElement);
	
//	mediaElementContainer.style.width =  '100%';
	
//	mediaElementContainer.onmouseenter = mediaElementContainer.onmousedown = function() {
//		adjustControls();
//		mediaControls.style.opacity = 1;
//		volumeControl.style.opacity = 1;
//	};

//	mediaElementContainer.onmouseleave = function() {
//		mediaControls.style.opacity = 0;
//		volumeControl.style.opacity = 0;
//	};

	return mediaElementContainer;
}


function adjustControls() {
	volumeControl.style.marginLeft = '3px';
	volumeControl.style.marginTop  = '3px';
}