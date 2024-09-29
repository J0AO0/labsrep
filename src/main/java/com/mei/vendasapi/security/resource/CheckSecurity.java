package com.mei.vendasapi.security.resource;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

    public @interface Usuario {

        @PreAuthorize("hasAuthority('C_USU')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeCadastrar {}

        @PreAuthorize("hasAuthority('D_USU')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeExcluir {}

        @PreAuthorize("hasAuthority('U_USU')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAtualizar {}

        @PreAuthorize("hasAuthority('R_USU')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar {}

        @PreAuthorize("hasAuthority('S_USU')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAlterarStatus {}

    }

    public @interface Pedido {

        @PreAuthorize("hasAuthority('C_PED')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeCadastrar {}

        @PreAuthorize("hasAuthority('D_PED')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeExcluir {}

        @PreAuthorize("hasAuthority('U_PED')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAtualizar {}

        @PreAuthorize("hasAuthority('R_PED')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar {}

        @PreAuthorize("hasAuthority('S_PED')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAlterarStatus {}

    }

    public @interface Produto {

        @PreAuthorize("hasAuthority('C_PROD')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeCadastrar {}

        @PreAuthorize("hasAuthority('D_PROD')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeExcluir {}

        @PreAuthorize("hasAuthority('U_PROD')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAtualizar {}

        @PreAuthorize("hasAuthority('R_PROD')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar {}

        @PreAuthorize("hasAuthority('S_PROD')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAlterarStatus {}

    }

    public @interface Empresa {

        @PreAuthorize("hasAuthority('C_EMP')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeCadastrar {}

        @PreAuthorize("hasAuthority('D_EMP')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeExcluir {}

        @PreAuthorize("hasAuthority('U_EMP')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAtualizar {}

        @PreAuthorize("hasAuthority('R_EMP')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar {}

        @PreAuthorize("hasAuthority('S_EMP')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAlterarStatus {}

    }

    public @interface Categoria {

        @PreAuthorize("hasAuthority('C_CAT')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeCadastrar {}

        @PreAuthorize("hasAuthority('D_CAT')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeExcluir {}

        @PreAuthorize("hasAuthority('U_CAT')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAtualizar {}

        @PreAuthorize("hasAuthority('R_CAT')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar {}

        @PreAuthorize("hasAuthority('S_CAT')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAlterarStatus {}

    }

}
