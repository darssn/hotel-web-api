
					



fetch('/clients').then(function(resp) {

		return resp.json();
	}).then(
			function(listeClient) {

				var empls = listeClient.map(
						function(client) {
							return '<option value="'+client.uuid+'">'
									+ client.nom + " " + client.prenoms
									+ '</option>';
						}).join("");

				document.getElementById('listClient').innerHTML = empls;
			})

	fetch('/chambres')
			.then(function(resp) {

				return resp.json();
			})
			.then(
					function(listeChambre) {

						var empls = listeChambre
								.map(
										function(chambre) {
											return '<div><input type="checkbox" id="'+chambre.uuid+'"class="chambreCheckbox" name="chambre" value="'+chambre.uuid+'"><label for="'+chambre.uuid+'">'
													+ chambre.uuid
													+ '</label></div>';
										}).join("");


						document.getElementById('checkChambre').innerHTML = "<legend>Veuillez Choisir une/des chambres :</legend>"
								+ empls;
	})
	
	


					
					

