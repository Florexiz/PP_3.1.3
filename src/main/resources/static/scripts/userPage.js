function pullPage() {
    fetch("/api/user").then(response => {
        response.json().then(user => {
            if (!user.roles.some(r => r.name == "ADMIN")) {
                $("#admin").remove();
            }
            $("#id").text(user.id);
            $("#firstName").text(user.firstName);
            $("#lastName").text(user.lastName);
            $("#age").text(user.age);
            $("#username").text(user.username);
            $("#roles").text(user.rolesAsString);
        });
    });
}

pullPage()