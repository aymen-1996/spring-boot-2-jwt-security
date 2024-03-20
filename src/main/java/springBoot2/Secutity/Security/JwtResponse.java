package springBoot2.Secutity.Security;


import springBoot2.Secutity.entities.User;

public class JwtResponse {
        private Long id;
        private String token;
        private String refreshtToken;
        private String role;
        private boolean enabled;
        private String image;
        private String nom;
        private String prenom;


        public JwtResponse(String token, String refreshtToken, String role , User user ) {
            if(user!=null) {
                this.id=user.getId();
                this.nom=user.getNom();
                this.prenom=user.getPrenom();
                this.image=user.getImage();
                this.role = user.getRole();

            }



            this.token = token;
            this.refreshtToken = refreshtToken;


        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }


        public String getRefreshtToken() {
            return refreshtToken;
        }

        public void setRefreshtToken(String refreshtToken) {
            this.refreshtToken = refreshtToken;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }


        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }


