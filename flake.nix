{
  inputs = {
    nixpkgs.url = "nixpkgs/nixpkgs-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, flake-utils, nixpkgs }:
    flake-utils.lib.eachSystem [ "x86_64-linux" ] (system:
      let
        pkgs = import nixpkgs { inherit system; };

        buildInputs = with pkgs; [ sbt sfml which ];
        nativeBuildInputs = with pkgs.llvmPackages_14; [ clang llvm ];
        testBuildInputs =
          (with pkgs; [ bash coreutils diffutils findutils gnugrep gnumake gnused ncurses ])
          ++ (with pkgs.xorg; [ xauth xorgserver xwd ]);
      in rec {
        devShell = pkgs.mkShell {
          name = "scala-native-sfml";
          packages = buildInputs ++ nativeBuildInputs ++ testBuildInputs;
        };

        packages.docker-ci = pkgs.dockerTools.buildImage {
          name = "scala-native-sfml-ci";

          config.Entrypoint = [ "${pkgs.dockerTools.binSh}/bin/sh" "-c" ];

          copyToRoot = pkgs.buildEnv {
            name = "scala-native-sfml-dependencies";
            paths = buildInputs ++ nativeBuildInputs ++ testBuildInputs
              ++ (with pkgs.dockerTools; [ binSh caCertificates fakeNss usrBinEnv ]);
            pathsToLink = [ "/bin" "/include" "/lib" "/usr" ];
          };

          runAsRoot = ''
            mkdir /run /tmp
            ln -s ${pkgs.mesa.drivers} /run/opengl-driver
          '';
        };
      });
}
