class Singleton:
    instance = None

    @staticmethod
    def get_instance():
        if Singleton.instance is None:
            Singleton.instance = Singleton()
        return Singleton.instance

    def __init__(self):
        if Singleton.instance is not None:
            raise Exception("This class is a Singleton. Use get_instance() to get the instance.")


singleton_instance = Singleton.get_instance()
